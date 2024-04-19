package com.fpetrola.z80.blocks;

import com.fpetrola.z80.instructions.base.DefaultTargetFlagInstruction;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import org.cojen.maker.Variable;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class VariableInstructionVisitor extends DummyInstructionVisitor<WordNumber> {
  public void setCreateInitializer(Function createInitializer) {
    this.createInitializer = createInitializer;
  }

  protected Function createInitializer;

  public void setBiConsumer(BiConsumer<Object, Variable> biConsumer) {
    this.biConsumer = biConsumer;
  }

  private BiConsumer<Object, Variable> biConsumer;
  protected Object sourceVariable;
  protected Object targetVariable;
  private OpcodeReference target;
  private ImmutableOpcodeReference source;
  private ByteCodeGenerator byteCodeGenerator;

  public VariableInstructionVisitor(BiConsumer<Object, Variable> biConsumer, ByteCodeGenerator byteCodeGenerator1) {
    this.biConsumer = biConsumer;
    byteCodeGenerator = byteCodeGenerator1;
  }

  public VariableInstructionVisitor(Function createInitializer, BiConsumer<Object, Variable> biConsumer, ByteCodeGenerator byteCodeGenerator1) {
    this.createInitializer = createInitializer;
    this.biConsumer = biConsumer;
    byteCodeGenerator = byteCodeGenerator1;
  }

  public void visitingTarget(OpcodeReference target, TargetInstruction targetInstruction) {
    this.target = target;
    OpcodeReferenceVisitor instructionVisitor = new OpcodeReferenceVisitor(true, byteCodeGenerator);
    if (createInitializer != null) instructionVisitor.setCreateInitializer(createInitializer);
    target.accept(instructionVisitor);
    targetVariable = instructionVisitor.getResult();
  }

  public void visitingSource(ImmutableOpcodeReference source, TargetSourceInstruction targetSourceInstruction) {
    this.source = source;
    OpcodeReferenceVisitor opcodeReferenceVisitor = new OpcodeReferenceVisitor(false, byteCodeGenerator);
    if (createInitializer != null) opcodeReferenceVisitor.setCreateInitializer(createInitializer);

    source.accept(opcodeReferenceVisitor);
    sourceVariable = opcodeReferenceVisitor.getResult();
  }

  public void visitingFlag(Register<WordNumber> flag, DefaultTargetFlagInstruction targetSourceInstruction) {
    OpcodeReferenceVisitor instructionVisitor = new OpcodeReferenceVisitor(true, byteCodeGenerator);
    if (createInitializer != null) instructionVisitor.setCreateInitializer(createInitializer);
    flag.accept(instructionVisitor);
  }

  public void visitingTargetSourceInstruction(TargetSourceInstruction targetSourceInstruction) {
    extracted();
  }

  private void extracted() {
    if (targetVariable instanceof Variable variable) {
      biConsumer.accept(sourceVariable, variable);
      setCommon(variable);
    }
  }

  private void setCommon(Variable variable) {
    String s = ByteCodeGeneratorVisitor.commonRegisters.get(variable.name());
    if (s != null) {
      if (!s.equals(variable.name())) {
        byteCodeGenerator.getExistingVariable(s).set(variable);
      }
    } else {
      Optional<Map.Entry<String, String>> first = ByteCodeGeneratorVisitor.commonRegisters.entrySet().stream().filter(e -> {
        return e.getKey().contains(",") && (e.getKey() + ",").contains(variable.name() + ",");
      }).findFirst();
      first.ifPresent(e -> {
        String[] split = e.getKey().split(",");
        byteCodeGenerator.getExistingVariable(e.getValue()).set(create16BitVariable(variable, split));
      });
    }
  }

  private Variable create16BitVariable(Variable variable, String[] split) {
    Variable h = variable8_Or_16(split[0], split[1], 1);
    Variable l = variable8_Or_16(split[1], split[0], 0);
    Variable result = h.shl(8).or(l.and(0xFF));
    return result;
  }

  private Variable variable8_Or_16(String first, String second, int insertIndex) {
    Variable result = null;
    boolean b = byteCodeGenerator.variableExists(first);
    if (!b) {
      StringBuffer stringBuffer = new StringBuffer(first);
      stringBuffer.insert(insertIndex, second.charAt(0));
      boolean b1 = byteCodeGenerator.variableExists(stringBuffer.toString());
      if (b1) {
        result = byteCodeGenerator.getExistingVariable(stringBuffer.toString());
      }
    } else result = byteCodeGenerator.getExistingVariable(first);
    return result;
  }

  public void visitingTargetInstruction(TargetInstruction targetInstruction) {
    extracted();
  }
}
