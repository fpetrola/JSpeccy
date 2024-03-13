package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;

public abstract class DefaultTargetInstruction<T extends WordNumber> extends AbstractInstruction<T> implements TargetInstruction<T> {
  protected OpcodeReference<T> target;
  protected FlagRegister<T> flag;

  public DefaultTargetInstruction(OpcodeReference<T> target, FlagRegister<T> flag) {
    this.target = target;
    this.flag = flag;
    incrementLengthBy(target.getLength());
  }

  @Override
  public OpcodeReference<T> getTarget() {
    return target;
  }

  @Override
  public void setTarget(OpcodeReference<T> target) {
    this.target = target;
  }

  public FlagRegister<T> getFlag() {
    return flag;
  }

  public void setFlag(FlagRegister<T> flag) {
    this.flag = flag;
  }
  @Override
  public String toString() {
    return super.toString() + " " + getTarget().toString();
  }
}