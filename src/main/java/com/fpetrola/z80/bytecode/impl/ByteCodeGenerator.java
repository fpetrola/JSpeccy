package com.fpetrola.z80.bytecode.impl;

import com.fpetrola.z80.cpu.RandomAccessInstructionFetcher;
import com.fpetrola.z80.instructions.Ret;
import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.routines.Routine;
import com.fpetrola.z80.transformations.*;
import org.cojen.maker.*;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ByteCodeGenerator {
  public Map<String, Variable> registers = new HashMap<>();
  public Field memory;
  public MethodMaker mm;
  private ClassMaker cm;
  private Map<Integer, Label> labels = new HashMap<>();
  private Set<Integer> positionedLabels = new HashSet<>();
  private Map<String, MethodMaker> methods = new HashMap<>();
  public final Routine routine;
  private RandomAccessInstructionFetcher instructionFetcher;
  private int startAddress;
  private Predicate<Integer> hasCodeAt;
  private int endAddress;
  public Register<WordNumber> pc;
  private Map<String, Variable> variables = new HashMap<>();
  public Map<String, VirtualRegister> registerByVariable = new HashMap<>();
  public Map<VirtualRegister, Variable> variablesByRegister = new HashMap<>();
  public Map<VirtualRegister<?>, VirtualRegister<?>> commonRegisters = new HashMap<>();
  private Map<Integer, Label> insertLabels = new HashMap<>();
  protected Field initial;
  private PendingFlagUpdate pendingFlag;

  public void setBranchLabel(Label branchLabel) {
    this.branchLabel = branchLabel;
  }

  private Label branchLabel;

  public ByteCodeGenerator(ClassMaker classMaker, RandomAccessInstructionFetcher randomAccessInstructionFetcher, Predicate<Integer> hasCodeChecker, Register pc, Map<String, MethodMaker> methods, Routine routine) {
    this.startAddress = routine.getStartAddress();
    this.endAddress = routine.getEndAddress();
    instructionFetcher = randomAccessInstructionFetcher;
    hasCodeAt = hasCodeChecker;
    this.pc = pc;
    cm = classMaker;
    this.methods = methods;
    this.routine = routine;
  }

  public static String createLabelName(int label) {
    //return "$" + Helper.convertToHex(label);
    return "$" + label;
  }

  public void generate() {
    mm = getMethod(startAddress);
    addInstructions();
  }

  private void addInstructions() {
    List<InstructionGenerator> generators = new ArrayList<>();

    Arrays.stream(RegisterName.values()).forEach(n -> addField(n.name()));
    addField("pops");
    //cm.addField(int.class, "initial").public_();
    // initial = mm.field("initial");
    // registers.put("initial", initial);

    //cm.addField(int[].class, "memory").public_();
    memory = mm.field("mem");
    //memory.set(mm.new_(int[].class, 0x10000));
    registers.put("mem", memory);

    Instruction[] lastInstruction = {null};
    final boolean[] ready = new boolean[]{false};

    Consumer<Integer> integerConsumer = address -> {
      Instruction instruction = instructionFetcher.getInstructionAt(address);
      if (instruction != null) {
        if (instruction != lastInstruction[0]) {
          boolean contains = routine.contains(address);
          if (contains) {
            if (!ready[0]) {
              //System.out.println(address);
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
                if (!ready[0]) {
                  if (address == 37425)
                    System.out.println("dsdsdf");
                  List<Routine> list = routine.innerRoutines.stream().filter(routine1 -> routine1.contains(address)).toList();
                  if (!list.isEmpty()) {
                    Routine first = list.getFirst();
                    if (first.getStartAddress() == address)
                      mm.invoke(createLabelName(first.getStartAddress()));
                    else {
                      System.out.println("inside routine");
                    }
                    //ready[0] = true;
                  } else {
                    if (!(instruction instanceof ConditionalInstruction<?, ?>) && pendingFlag != null)
                      pendingFlag.update();

                    pc.write(WordNumber.createValue(address));
                    int label = -1;
                    if (getLabel(address) != null) {
                      label = firstAddress;
                      hereLabel(label);
                    }

                    if (instruction instanceof Ret && routine.virtualPop.contains(address)) {
                      mm.invoke("incPops");
                    }

                    ByteCodeGeneratorVisitor visitor = new ByteCodeGeneratorVisitor(mm, label, this, address, pendingFlag);
                    instruction.accept(visitor);

                    pendingFlag = visitor.pendingFlag;

                    if (routine.virtualPop.contains(address)) {
                      mm.invoke("incPops");
                    }
                  }
                }

              };

              generators.add(new InstructionGenerator(scopeAdjuster, labelGenerator, instructionGenerator));
            }
          }
        }
        lastInstruction[0] = instruction;
      }
    };
    forEachAddress(integerConsumer);

    generators.forEach(g -> g.scopeAdjuster().run());
    generators.forEach(g -> g.scopeAdjuster().run());
    generators.forEach(g -> g.labelGenerator().run());
    removeExternalLabels();
    generators.forEach(g -> g.instructionGenerator().run());

    positionedLabels.forEach(l -> labels.get(l).here());
  }

  private void addField(String name) {
    // cm.addField(int.class, name).private_().static_();
    Variable field = mm.field(name);
    registers.put(name, field);

    if (name.length() == 2) field = new Composed16BitRegisterVariable(mm, name);

    variables.put(name, field);
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
    if (insertLabel != null) insertLabel.here();

    Label label = getLabel(labelName);
    if (label == null) {
      label = addLabel(labelName);
    }

    label.here();
    positionedLabels.remove((Object) labelName);

  }

  public void forEachAddress(Consumer<Integer> consumer) {
    for (int i = this.startAddress; i <= endAddress; i++)
      consumer.accept(i);
  }

  public MethodMaker getMethod(int jumpLabel) {
    return createMethod(jumpLabel);
  }

  public MethodMaker createMethod(int jumpLabel) {
    return findMethod(createLabelName(jumpLabel), methods, cm);
  }

  public static MethodMaker findMethod(String methodName, Map<String, MethodMaker> methods, ClassMaker classMaker) {
    MethodMaker methodMaker = methods.get(methodName);
    if (methodMaker == null) {
      methodMaker = classMaker.addMethod(void.class, methodName).public_();
      methods.put(methodName, methodMaker);
    }

    return methodMaker;
  }

  public <T extends WordNumber> Variable getField(String name) {
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
    if (value1 != null) set = var.set(value1);
    return set;
  }


  public <T extends WordNumber> Variable getExistingVariable(VirtualRegister<?> register) {
    register = getTop(register);

    String registerName = getRegisterName(register);
    return variables.get(registerName);
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
    VirtualRegister o = (VirtualRegister) register.getVersionHandler().versions.get(0);
    return o;
    //register = register.getVersionHandler().getBiggestScopeFor(register);

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
    //return register;
  }

}
