package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.State;

public abstract class ConditionalInstruction extends TargetInstruction {

  protected Condition condition;

  public ConditionalInstruction(State state, OpcodeReference target, Condition condition) {
    super(state, target);
    this.condition = condition;
  }

  public String toString() {
    String conditionStr = condition.toString();
    return getClass().getSimpleName() + " " + ((conditionStr.length() > 0) ? conditionStr + ", " : "") + target;
  }

  public Condition getCondition() {
    return condition;
  }
}