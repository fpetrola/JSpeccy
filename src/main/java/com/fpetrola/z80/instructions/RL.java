package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class RL extends TargetOpCode {

  public RL(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {

    pc.increment(1);

    final int value = target.read();

    target.write(flag.shiftGenericRL(value));

    return 4 + target.cyclesCost() + target.cyclesCost();
  }

  @Override
  public String toString() {
    return "RL " + target;
  }
}
