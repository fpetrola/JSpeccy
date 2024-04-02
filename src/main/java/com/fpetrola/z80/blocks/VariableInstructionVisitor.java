package com.fpetrola.z80.blocks;

import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.transformations.Virtual8BitsRegister;
import org.cojen.maker.Variable;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class VariableInstructionVisitor extends DummyInstructionVisitor<WordNumber> {
  private BiFunction<ByteCodeGenerator, Virtual8BitsRegister, Object> createInitializer;
  private BiConsumer<Object, Variable> biConsumer;
  private Object sourceVariable;
  private Object targetVariable;
  private OpcodeReference target;
  private ImmutableOpcodeReference source;
  private ByteCodeGenerator byteCodeGenerator;

  public VariableInstructionVisitor(BiConsumer<Object, Variable> biConsumer, ByteCodeGenerator byteCodeGenerator1) {
    this.biConsumer = biConsumer;
    byteCodeGenerator = byteCodeGenerator1;
  }

  public VariableInstructionVisitor(BiFunction<ByteCodeGenerator, Virtual8BitsRegister, Object> createInitializer, BiConsumer<Object, Variable> biConsumer, ByteCodeGenerator byteCodeGenerator1) {
    this.createInitializer = createInitializer;
    this.biConsumer = biConsumer;
    byteCodeGenerator = byteCodeGenerator1;
  }

  public void visitingTarget(OpcodeReference target, TargetInstruction targetInstruction) {
    this.target = target;
    OpcodeReferenceVisitor instructionVisitor = new OpcodeReferenceVisitor(true, byteCodeGenerator);
    if (createInitializer !=null)
      instructionVisitor.setCreateInitializer(createInitializer);
    target.accept(instructionVisitor);
    targetVariable = instructionVisitor.getResult();
  }

  public void visitingSource(ImmutableOpcodeReference source, TargetSourceInstruction targetSourceInstruction) {
    this.source = source;
    OpcodeReferenceVisitor opcodeReferenceVisitor = new OpcodeReferenceVisitor(false, byteCodeGenerator);
    if (createInitializer !=null)
      opcodeReferenceVisitor.setCreateInitializer(createInitializer);

    source.accept(opcodeReferenceVisitor);
    sourceVariable = opcodeReferenceVisitor.getResult();
  }

  public void visitingFlag(Register<WordNumber> flag, TargetSourceInstruction targetSourceInstruction) {
  }

  public void visitingTargetSourceInstruction(TargetSourceInstruction targetSourceInstruction) {
    extracted();
  }

  private void extracted() {
    if (targetVariable instanceof Variable variable)
      biConsumer.accept(sourceVariable, variable);
  }

  public void visitingTargetInstruction(TargetInstruction targetInstruction) {
    extracted();
  }
}
