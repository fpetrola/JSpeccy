package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class JR extends AbstractOpCode {

  public final Condition condition;
  private final OpcodeReference target;

  public JR(State state, Condition condition, OpcodeReference target) {
    super(state);
    this.target = target;
    this.condition = condition;
  }

  @Override
  public int execute() {
    if (condition.conditionMet()) {
      pc.increment(1);
      pc.increment((byte) target.read());
      return 4 + 5 + target.cyclesCost();
    } else {
      pc.increment(2);
      return 4 + target.cyclesCost();
    }
  }

  @Override
  public String toString() {
    String conditionStr = condition.toString();
    return "JR " + ((conditionStr.length() > 0) ? conditionStr + ", " : "") + target;
  }
}
