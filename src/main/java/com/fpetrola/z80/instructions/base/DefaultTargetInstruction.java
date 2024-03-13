package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public abstract class DefaultTargetInstruction<T extends WordNumber> extends AbstractInstruction<T> implements TargetInstruction<T> {
  protected OpcodeReference<T> target;

  public DefaultTargetInstruction(OpcodeReference<T> target) {
    this.target = target;
  }

  @Override
  public OpcodeReference<T> getTarget() {
    return target;
  }

  @Override
  public void setTarget(OpcodeReference<T> target) {
    this.target = target;
  }

  @Override
  public String toString() {
    return super.toString() + " " + getTarget().toString();
  }
}
