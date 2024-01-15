package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class RLA extends TargetOpCode {

  public RLA(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {

    final int value = target.read();
    target.write(flag.RLA(value));

    return cyclesCost;
  }
}
