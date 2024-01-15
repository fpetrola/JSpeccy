package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class RL extends TargetOpCode {

  public RL(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    final int value = target.read();
    target.write(flag.shiftGenericRL(value));
    return cyclesCost;
  }
}
