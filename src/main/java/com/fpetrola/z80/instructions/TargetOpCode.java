package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public abstract class TargetOpCode extends AbstractOpCode {

  protected final OpcodeReference target;

  public TargetOpCode(State state, OpcodeReference target) {
    super(state);
    this.target = target;
  }

  public OpcodeReference getTarget() {
    return target;
  }

  public int getLength() {
    return target.getLength() + super.getLength();
  }
}