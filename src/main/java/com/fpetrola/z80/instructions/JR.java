package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class JR extends ConditionalOpcode {

  public JR(State state, OpcodeReference target, Condition condition) {
    super(state, target, condition);
  }

  public int execute() {
    byte by = (byte) target.read();
    if (condition.conditionMet()) {
      int position = pc.read() + by + 1;
      state.setNextPC(position);
//      pc.increment(by);
      return 4 + 5 + target.cyclesCost();
    } else {
//      pc.increment(1);
      return getCyclesCost();
    }
  }

  public String toString() {
    String conditionStr = condition.toString();
    return "JR " + ((conditionStr.length() > 0) ? conditionStr + ", " : "") + target;
  }
}
