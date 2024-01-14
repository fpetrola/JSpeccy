package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class JR extends TargetOpCode {
  public final Condition condition;

  public JR(State state, Condition condition, OpcodeReference target) {
    super(state, target);
    this.condition = condition;
  }

  public int execute() {
    if (condition.conditionMet()) {
      byte by = (byte) target.read();
      int position= pc.read()+by;
      state.setNextPC(position);
//      pc.increment(by);
      return 4 + 5 + target.cyclesCost();
    } else {
      pc.increment(1);
      return 4 + target.cyclesCost();
    }
  }

  public String toString() {
    String conditionStr = condition.toString();
    return "JR " + ((conditionStr.length() > 0) ? conditionStr + ", " : "") + target;
  }
}
