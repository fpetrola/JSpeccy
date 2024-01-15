package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class RRA extends TargetOpCode {

  public RRA(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    final int value = target.read();
    target.write(flag.RRA(value));

    return cyclesCost;
  }
}
