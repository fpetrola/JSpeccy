package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public abstract class DefaultTargetFlagInstruction<T extends WordNumber> extends DefaultTargetInstruction<T> {
  protected Register<T> flag;

  public DefaultTargetFlagInstruction(OpcodeReference<T> target, Register<T> flag) {
    super(target);
    this.flag = flag;
    incrementLengthBy(target.getLength());
  }

  public Register<T> getFlag() {
    return flag;
  }

  public void setFlag(Register<T> flag) {
    this.flag = flag;
  }

  public void accept(InstructionVisitor visitor) {
    visitor.visitingTarget(getTarget(), this);
    visitor.visitingFlag(flag, this);
    visitor.visitingTargetInstruction(this);
  }
}