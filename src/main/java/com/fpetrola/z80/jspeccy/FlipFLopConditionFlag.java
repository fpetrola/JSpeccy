package com.fpetrola.z80.jspeccy;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.opcodes.references.Condition;

public class FlipFLopConditionFlag implements Condition {
  private final Condition condition;
  private boolean state = false;

  public FlipFLopConditionFlag(Condition condition) {
    this.condition = condition;
  }

  public boolean conditionMet() {
    boolean result = state;
    state = !state;
    return Math.random() * 100 > 50;
  }

  @Override
  public void accept(InstructionVisitor visitor) {

  }

  @Override
  public String toString() {
    return condition.toString();
  }
}
