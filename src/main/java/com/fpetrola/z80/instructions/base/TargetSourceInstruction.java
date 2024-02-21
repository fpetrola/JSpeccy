package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.blocks.ByteCodeGenerator;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import org.cojen.maker.MethodMaker;
import org.cojen.maker.Variable;

public abstract class TargetSourceInstruction<T extends WordNumber> extends TargetInstruction<T> {

  protected final ImmutableOpcodeReference<T> source;

  public TargetSourceInstruction(State state, OpcodeReference<T> target, ImmutableOpcodeReference<T> source) {
    super(state, target);
    this.source = source;
    incrementLengthBy(source.getLength());
    cyclesCost += 1;
  }

  public String toString() {
    return super.toString() + ", " + source;
  }

  public ImmutableOpcodeReference<T> getSource() {
    return source;
  }

  @Override
  public int createBytecode(MethodMaker mm, int label, ByteCodeGenerator byteCodeGenerator) {
    hereLabel(label, byteCodeGenerator);

    Object sourceVariable = getSourceVariableOf(byteCodeGenerator, source, false);
    Object targetVariable = getSourceVariableOf(byteCodeGenerator, target, true);
    doOperation(targetVariable, sourceVariable);

    return 0;
  }

  protected void doOperation(Object targetVariable, Object sourceVariable) {
    if (targetVariable instanceof Variable)
      ((Variable) targetVariable).set(sourceVariable);
  }
}