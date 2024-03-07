package com.fpetrola.z80.blocks;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.spy.InstructionSpy;
import org.apache.commons.io.FileUtils;
import org.cojen.maker.*;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ByteCodeGenerator {
  public CodeBlock codeBlock;
  public InstructionSpy spy;
  public Map<String, Field> registers = new HashMap<>();
  public Field memory;
  private MethodMaker mm;
  private ClassMaker cm;
  private Map<Integer, Label> labels = new HashMap<>();
  private Set<Integer> positionedLabels = new HashSet<>();
  private Map<Integer, MethodMaker> methods = new HashMap<>();

  public ByteCodeGenerator(CodeBlock codeBlock, InstructionSpy spy) {
    this.codeBlock = codeBlock;
    this.spy = spy;
  }

  public void generate() {
    cm = ClassMaker.beginExternal("JSW").public_();

    mm = getMethod(codeBlock.getRangeHandler().getStartAddress());

    Arrays.stream(RegisterName.values()).forEach(n -> {
      cm.addField(int.class, n.name()).public_();
      registers.put(n.name(), mm.field(n.name()));
    });
    cm.addField(int[].class, "memory").public_();
    memory = mm.field("memory");

    Instruction[] lastInstruction = {null};
    int[] firstAddress = {0};

    forEachAddress(address -> {
      Instruction instruction = spy.getFetchedAt(address);
      if (instruction != null) {
        if (instruction != lastInstruction[0]) {
          JumpLabelVisitor jumpLabelVisitor = new JumpLabelVisitor();
          instruction.accept(jumpLabelVisitor);
          int jumpLabel = jumpLabelVisitor.getJumpLabel();

          if (jumpLabel > 0)
            addLabel(jumpLabel);
        }
        lastInstruction[0] = instruction;
      }
    });

    removeExternalLabels();

    forEachAddress(address -> {
      Instruction instruction = spy.getFetchedAt(address);
      if (instruction != null) {
        if (instruction != lastInstruction[0]) {
          firstAddress[0] = address;
          int label = -1;
          if (getLabel(address) != null) {
            label = firstAddress[0];
            System.out.println(address);
          }

          InstructionVisitor byteCodeGeneratorVisitor = new ByteCodeGeneratorVisitor(mm, label, this);
          instruction.accept(byteCodeGeneratorVisitor);
        }
        lastInstruction[0] = instruction;
      }
    });

    try {
      byte[] bytes = cm.finishBytes();
      FileUtils.writeByteArrayToFile(new File("JSW.class"), bytes);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void removeExternalLabels() {
    List<Integer> collect = labels.keySet().stream().filter(l -> {
      boolean b = l < codeBlock.getRangeHandler().getStartAddress();
      boolean notCodeBlock = !(codeBlock.blocksManager.findBlockAt(l) instanceof CodeBlock);
      boolean isNotFetched = spy.getFetchedAt(l) == null;
      return b || notCodeBlock || isNotFetched;
    }).collect(Collectors.toList());
    collect.forEach(l -> {
      labels.remove(l);
      positionedLabels.remove((Object) l);
    });

  }

  public Label addLabel(int s) {
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
    Label label = getLabel(labelName);
    if (label == null)
      label = addLabel(labelName);

    label.here();
    positionedLabels.remove((Object) labelName);
  }

  public void forEachAddress(Consumer<Integer> consumer) {
    int startAddress = codeBlock.getRangeHandler().getStartAddress();
    int start = startAddress;
    start = 37310;
    for (int i = start; i < 0xFFFF; i++) {
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

  public <T extends WordNumber> Field getField(Register register) {
    return registers.get(register.getName().name());
  }
}
