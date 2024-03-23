package com.fpetrola.z80.blocks;

import com.fpetrola.z80.cpu.RandomAccessInstructionFetcher;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import org.apache.commons.io.FileUtils;
import org.cojen.maker.*;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
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
  private Label branchLabel;

  public ByteCodeGenerator(RandomAccessInstructionFetcher randomAccessInstructionFetcher, int startAddress, Predicate<Integer> hasCodeChecker, int endAddress, Register pc) {
    this.startAddress = startAddress;
    instructionFetcher = randomAccessInstructionFetcher;
    hasCodeAt = hasCodeChecker;
    this.endAddress = endAddress;
    this.pc = pc;
  }

  public void generate() {
    cm = ClassMaker.beginExternal("JSW").public_();

    mm = getMethod(startAddress);

//    Arrays.stream(RegisterName.values()).forEach(n -> addField(n.name()));

    addField("F");

    cm.addField(int[].class, "memory").public_();
    memory = mm.field("memory");
    registers.put("memory", memory);

    Instruction[] lastInstruction = {null};

    forEachAddress(address -> {
      Instruction instruction = instructionFetcher.getInstructionAt(address);
      if (instruction != null) {
        if (instruction != lastInstruction[0]) {
          int firstAddress = address;

          Runnable labelGenerator = () -> {
            pc.write(WordNumber.createValue(address));
            JumpLabelVisitor jumpLabelVisitor1 = new JumpLabelVisitor();
            instruction.accept(jumpLabelVisitor1);
            int jumpLabel = jumpLabelVisitor1.getJumpLabel();

            if (jumpLabel > 0)
              addLabel(jumpLabel);
          };
          Runnable instructionGenerator = () -> {
            pc.write(WordNumber.createValue(address));
            int label = -1;
            if (getLabel(address) != null) {
              label = firstAddress;
            }

            InstructionVisitor byteCodeGeneratorVisitor = new ByteCodeGeneratorVisitor(mm, label, ByteCodeGenerator.this);
            instruction.accept(byteCodeGeneratorVisitor);
          };

          generators.add(new InstructionGenerator(labelGenerator, instructionGenerator));
        }
        lastInstruction[0] = instruction;
      }
    });

    generators.forEach(g -> g.labelGenerator().run());
    removeExternalLabels();
    generators.forEach(g -> g.instructionGenerator().run());

    try {
      byte[] bytes = cm.finishBytes();
      FileUtils.writeByteArrayToFile(new File("JSW.class"), bytes);
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

  public Label addLabel(int s) {
    Label label0 = mm.label();
    labels.put(s + 1000, label0);

    Label label = mm.label();
    labels.put(s, label);
    positionedLabels.add(s);
    return label;
  }

  public Label getLabel(int i) {
    return labels.get(i);
  }

  public boolean isLabelPositioned(int labelName) {
    return positionedLabels.contains(labelName);
  }

  public void hereLabel(int labelName) {
    if (branchLabel == null) {
      branchLabel = getLabel(labelName + 1000);
      branchLabel.here();
    }

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
    for (int i = start; i < endAddress; i++) {
      consumer.accept(i);
    }
  }

  public MethodMaker getMethod(int jumpLabel) {
    return createMethod(jumpLabel);
  }

  private MethodMaker createMethod(int jumpLabel) {
    MethodMaker methodMaker = methods.get(jumpLabel);
    if (methodMaker == null) {
      methodMaker = cm.addMethod(void.class, ByteCodeGeneratorVisitor.createLabelName(jumpLabel)).public_();
      if (!registers.isEmpty()) {
        Field field = methodMaker.field(RegisterName.E.name());
        Field field1 = methodMaker.field(RegisterName.B.name());
        field.set(field1);
      }
      methods.put(jumpLabel, methodMaker);
    }

    return methodMaker;
  }

  public <T extends WordNumber> Field getField(String name) {
    return registers.get(name);
  }

  public <T extends WordNumber> Variable getVariable(String name, Object value) {
    Variable variable = variables.get(name);
    if (variable != null)
      return variable;
    else {
      Variable var = mm.var(int.class).set(value);
      var.name(name);
      variables.put(name, var);

//      getField("PC").sub(var);
      return var;
    }
  }

  public Label getBranchLabel() {
    return branchLabel;
  }
}
