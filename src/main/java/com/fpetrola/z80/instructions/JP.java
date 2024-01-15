package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class JP extends ConditionalOpcode {

  public JP(State state, OpcodeReference target, Condition condition) {
    super(state, target, condition);
  }

  public int execute() {
    final int position = target.read();

    if (condition.conditionMet()) {
      state.setNextPC(position);

//      pc.write(position);
      memptr.write(position);
    }

    return cyclesCost;
  }

  public String toString() {
    return "JP " + target;
  }
}
