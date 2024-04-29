package com.fpetrola.z80.opcodes.references;

import java.util.function.Predicate;

public abstract class ConditionBase implements Condition {
  public Predicate<Boolean> isConditionMet;

  public ConditionBase(Predicate<Boolean> isConditionMet) {
    this.isConditionMet = isConditionMet;
  }

  public ConditionBase() {
    this((b) -> b);
  }

  protected boolean filterCondition(boolean result) {
    return isConditionMet.test(result);
  }
}
