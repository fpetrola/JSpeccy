package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.blocks.ByteCodeGenerator;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.opcodes.references.MutableOpcodeReference;
import org.cojen.maker.MethodMaker;
import org.cojen.maker.Variable;

public abstract class TargetInstruction<T extends WordNumber> extends AbstractInstruction<T> {

  protected OpcodeReference<T> target;

  public TargetInstruction(OpcodeReference<T> target) {
    this.target = target;
    incrementLengthBy(target.getLength());
  }

  public OpcodeReference<T> getTarget() {
    return target;
  }

  public void setTarget(OpcodeReference<T> target) {
    this.target = target;
  }

  public String toString() {
    return super.toString() + " " + target.toString();
  }

  @Override
  public int createBytecode(MethodMaker mm, int label, ByteCodeGenerator byteCodeGenerator) {
    hereLabel(label, byteCodeGenerator);

    Object targetVariable = getSourceVariableOf(byteCodeGenerator, target, true);
    doOperation(targetVariable, 0);
    return 0;
  }

  protected void doOperation(Object targetVariable, Object sourceVariable) {
    if (targetVariable instanceof Variable)
      ((Variable) targetVariable).set(sourceVariable);
  }
}