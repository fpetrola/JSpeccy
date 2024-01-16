package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class JR extends ConditionalOpcode {

  public JR(State state, OpcodeReference target, Condition condition) {
    super(state, target, condition);
  }

  public int execute() {
    byte by = (byte) target.read();
    if (condition.conditionMet()) {
      int position = pc.read() + length + by;
      state.setNextPC(position);
    }

    return cyclesCost;
  }
}
