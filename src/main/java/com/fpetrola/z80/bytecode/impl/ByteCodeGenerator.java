package com.fpetrola.z80.bytecode.impl;

import com.fpetrola.z80.cpu.RandomAccessInstructionFetcher;
import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.transformations.*;
import org.apache.commons.io.FileUtils;
import org.cojen.maker.*;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ByteCodeGenerator {
  public Map<String, Field> registers = new HashMap<>();
  public Field memory;
  private MethodMaker mm;
  private ClassMaker cm;
  private Map<Integer, Label> labels = new HashMap<>();
  private Set<Integer> positionedLabels = new HashSet<>();
  private Map<Integer, MethodMaker> methods = new HashMap<>();
  private RandomAccessInstructionFetcher instructionFetcher;
  private int startAddress;
  private Predicate<Integer> hasCodeAt;
  private List<InstructionGenerator> generators = new ArrayList<>();
  private int endAddress;
  private Register<WordNumber> pc;
  private Map<String, Variable> variables = new HashMap<>();
  public Map<String, VirtualRegister> registerByVariable = new HashMap<>();
  public Map<VirtualRegister, Variable> variablesByRegister = new HashMap<>();
  public Map<VirtualRegister<?>, VirtualRegister<?>> commonRegisters = new HashMap<>();
  private Map<Integer, Label> insertLabels = new HashMap<>();
  protected Field initial;

  public void setBranchLabel(Label branchLabel) {
    this.branchLabel = branchLabel;
  }

  private Label branchLabel;

  public ByteCodeGenerator(RandomAccessInstructionFetcher randomAccessInstructionFetcher, int startAddress, Predicate<Integer> hasCodeChecker, int endAddress, Register pc) {
    this.startAddress = startAddress;
    instructionFetcher = randomAccessInstructionFetcher;
    hasCodeAt = hasCodeChecker;
    this.endAddress = endAddress;
    this.pc = pc;
  }

  public static String createLabelName(int label) {
    return "$" + Helper.convertToHex(label);
  }

  public byte[] generate(Supplier<ClassMaker> classMakerSupplier, String pathname) {
    cm = classMakerSupplier.get();

    mm = getMethod(startAddress);

//    Arrays.stream(RegisterName.values()).forEach(n -> addField(n.name()));
    cm.addField(int.class, "initial").public_();
    initial = mm.field("initial");
    registers.put("initial", initial);

    cm.addField(int[].class, "memory").public_();
    memory = mm.field("memory");
    //memory.set(mm.new_(int[].class, 0x10000));
    registers.put("memory", memory);

    Instruction[] lastInstruction = {null};

    forEachAddress(address -> {
      Instruction instruction = instructionFetcher.getInstructionAt(address);
      if (instruction != null) {
        if (instruction != lastInstruction[0]) {
          pc.write(WordNumber.createValue(address));
          int firstAddress = address;

//          GenerateTestSourceInstructionVisitor visitor = new GenerateTestSourceInstructionVisitor(startAddress);
//          instruction.accept(visitor);
//          System.out.println(visitor.result);

          Runnable scopeAdjuster = () -> {
            pc.write(WordNumber.createValue(address));
            new InstructionActionExecutor<>(r -> r.adjustRegisterScope()).executeAction(instruction);
          };

          Runnable labelGenerator = () -> {
            pc.write(WordNumber.createValue(address));
            JumpLabelVisitor jumpLabelVisitor1 = new JumpLabelVisitor();
            instruction.accept(jumpLabelVisitor1);
            int jumpLabel = jumpLabelVisitor1.getJumpLabel();

            //  if (jumpLabel > 0)
            addLabel(address);
          };
          Runnable instructionGenerator = () -> {
            pc.write(WordNumber.createValue(address));
            int label = -1;
            if (getLabel(address) != null) {
              label = firstAddress;
              hereLabel(label);
            }

            instruction.accept(new ByteCodeGeneratorVisitor(mm, label, this));
          };

          generators.add(new InstructionGenerator(scopeAdjuster, labelGenerator, instructionGenerator));
        }
        lastInstruction[0] = instruction;
      }
    });

    generators.forEach(g -> g.scopeAdjuster().run());
    generators.forEach(g -> g.scopeAdjuster().run());
    generators.forEach(g -> g.labelGenerator().run());
    removeExternalLabels();
    generators.forEach(g -> g.instructionGenerator().run());

    positionedLabels.forEach(l -> labels.get(l).here());

    try {
      byte[] bytes = cm.finishBytes();
      FileUtils.writeByteArrayToFile(new File("target/" + pathname), bytes);
      return bytes;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void addField(String name) {
    cm.addField(int.class, name).public_();
    registers.put(name, mm.field(name));
  }

  private void removeExternalLabels() {
    List<Integer> collect = labels.keySet().stream().filter(l -> {
      boolean b = l < startAddress;
      return b || !hasCodeAt.test(l);
    }).collect(Collectors.toList());
    collect.forEach(l -> {
      labels.remove(l);
      positionedLabels.remove((Object) l);
    });

  }

  public Label addLabel(int labelLine) {
    Label label = labels.get(labelLine);
    if (label == null) {
      label = mm.label();
      labels.put(labelLine, label);
      positionedLabels.add(labelLine);

      if (insertLabels.get(labelLine) == null) {
        insertLabels.put(labelLine, mm.label());
      }
    }

    return label;
  }

  public Label getLabel(int i) {
    return labels.get(i);
  }

  public boolean isLabelPositioned(int labelName) {
    return positionedLabels.contains(labelName);
  }

  public void hereLabel(int labelName) {
    Label insertLabel = insertLabels.get(labelName);
    if (insertLabel != null)
      insertLabel.here();

    Label label = getLabel(labelName);
    if (label == null) {
      label = addLabel(labelName);
    }

    label.here();
    positionedLabels.remove((Object) labelName);

  }

  public void forEachAddress(Consumer<Integer> consumer) {
    int startAddress = this.startAddress;
    int start = startAddress;
//    start = 37310;
    for (int i = start; i <= endAddress; i++) {
      consumer.accept(i);
    }
  }

  public MethodMaker getMethod(int jumpLabel) {
    return createMethod(jumpLabel);
  }

  private MethodMaker createMethod(int jumpLabel) {
    MethodMaker methodMaker = methods.get(jumpLabel);
    if (methodMaker == null) {
      methodMaker = cm.addMethod(void.class, createLabelName(jumpLabel)).public_();
//      if (!registers.isEmpty()) {
//        Field field = methodMaker.field(RegisterName.E.name());
//        Field field1 = methodMaker.field(RegisterName.B.name());
//        field.set(field1);
//      }
      methods.put(jumpLabel, methodMaker);
    }

    return methodMaker;
  }

  public <T extends WordNumber> Field getField(String name) {
    return registers.get(name);
  }

  public <T extends WordNumber> boolean variableExists(VirtualRegister register) {
    register = getTop(register);
    Variable variable = variables.get(getRegisterName(register));
    return variable != null;
  }

  public <T extends WordNumber> void createVariable(VirtualRegister register1) {
    VirtualRegister register = getTop(register1);
    String name = getRegisterName(register);
    registerByVariable.put(name, register);
  }

  public <T extends WordNumber> Variable getVariable(VirtualRegister register1, Supplier<Object> value) {
    VirtualRegister register = getTop(register1);

    String name = getRegisterName(register);
    Variable variable = variables.get(name);
    if (variable != null) {
      Variable set = doSetValue(value, variable);
      variables.put(name, set);
      variablesByRegister.put(register, set);
      return variable;
    } else {
//      System.out.println("creating var: " + name + "= " + value);
      registerByVariable.put(name, register);

      Variable set = setVariable(name, value);

      variables.put(name, set);
      variablesByRegister.put(register, set);

//      getField("PC").sub(var);
      return set;
    }
  }

  public Variable setVariable(String name, Supplier<Object> value) {
    Variable var = mm.var(int.class);
    var.name(name);
    Variable set = doSetValue(value, var);
    return set;
  }

  private Variable doSetValue(Supplier<Object> value, Variable var) {
    Variable set = var;
    Object value1 = value.get();
    if (value1 != null)
      set = var.set(value1);
    return set;
  }


  public <T extends WordNumber> Variable getExistingVariable(VirtualRegister<?> register) {
    register = getTop(register);

    return variables.get(getRegisterName(register));
  }

  public Label getBranchLabel(Integer minLine) {
    Optional<Map.Entry<Integer, Label>> first = insertLabels.entrySet().stream().sorted(Comparator.comparingInt(Map.Entry::getKey)).filter(e -> e.getKey() >= minLine).findFirst();
    return first.get().getValue();
  }

  public static String getRegisterName(VirtualRegister register) {
    Virtual8BitsRegister.breakInStackOverflow();

    return VirtualComposed16BitRegister.fixIndexNames(register.getName().replace(",", ""));
  }

  public static VirtualRegister getTop(VirtualRegister<?> register) {
    register = register.getVersionHandler().getBiggestScopeFor(register);

//    if (register == null)
//      return null;
//    while (register.getPreviousVersions().size() == 1) {
//      VirtualRegister<?> previous = register.getPreviousVersions().get(0);
//      boolean initial = previous instanceof InitialVirtualRegister<?>;
//      boolean mixed = previous.getName().contains(",");
//      boolean isLd = false;
//      if (register instanceof Virtual8BitsRegister<?> virtual8BitsRegister)
//        isLd = virtual8BitsRegister.instruction instanceof Ld<?>;
//
//      if (initial || mixed || isLd) break;
//      register = previous;
//    }
    return register;
  }

}
