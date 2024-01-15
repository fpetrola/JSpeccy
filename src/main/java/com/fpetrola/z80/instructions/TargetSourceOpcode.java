package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public abstract class TargetSourceOpcode extends TargetOpCode {

  protected final OpcodeReference source;

  public TargetSourceOpcode(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target);
    this.source = source;
    source.setOpCode(this);
    incrementLengthBy(source.getLength());
    cyclesCost += source.cyclesCost();
  }
}