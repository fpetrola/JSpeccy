package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public abstract class ConditionalOpcode extends TargetOpCode {

  protected Condition condition;

  public ConditionalOpcode(State state, OpcodeReference target, Condition condition) {
    super(state, target);
    this.condition = condition;
  }

}