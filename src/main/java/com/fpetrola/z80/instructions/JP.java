package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class JP extends TargetOpCode {

  public final Condition condition;

  public JP(State state, Condition condition, OpcodeReference target) {
    super(state, target);
    this.condition = condition;
  }

  public int execute() {
    final int position = target.read();

    if (condition.conditionMet()) {
      state.setNextPC(position);

//      pc.write(position);
      memptr.write(position);
    }

    return getCyclesCost();
  }

  public String toString() {
    return "JP " + target;
  }
}
