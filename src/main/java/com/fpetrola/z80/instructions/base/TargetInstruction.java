package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;

public abstract class TargetInstruction<T extends WordNumber> extends AbstractInstruction<T> {
  protected OpcodeReference<T> target;
  protected FlagRegister<T> flag;

  public TargetInstruction(OpcodeReference<T> target, FlagRegister<T> flag) {
    this.target = target;
    this.flag = flag;
    incrementLengthBy(target.getLength());
  }

  public OpcodeReference<T> getTarget() {
    return target;
  }

  public void setTarget(OpcodeReference<T> target) {
    this.target = target;
  }

  public FlagRegister<T> getFlag() {
    return flag;
  }

  public void setFlag(FlagRegister<T> flag) {
    this.flag = flag;
  }

  public String toString() {
    return super.toString() + " " + target.toString();
  }

  public void accept(InstructionVisitor visitor) {
    visitor.visitingTarget(target, this);
    visitor.visitingTargetInstruction(this);
  }
}