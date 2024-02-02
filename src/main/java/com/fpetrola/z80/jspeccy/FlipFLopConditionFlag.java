package com.fpetrola.z80.jspeccy;

import com.fpetrola.z80.opcodes.references.Condition;

public class FlipFLopConditionFlag implements Condition {
  private boolean state = false;

  public boolean conditionMet() {
    boolean result = state;
    state = !state;
    return Math.random() * 100 > 50;
  }
}
