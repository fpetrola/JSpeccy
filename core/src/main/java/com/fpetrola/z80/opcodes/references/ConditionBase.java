package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.jspeccy.ConditionPredicate;

import java.util.function.Predicate;

public abstract class ConditionBase implements Condition {
  public ConditionPredicate<Boolean> isConditionMet;

  public ConditionBase(ConditionPredicate<Boolean> isConditionMet) {
    this.isConditionMet = isConditionMet;
  }

  public ConditionBase() {
    this((b, i) -> b);
  }

  protected boolean filterCondition(boolean result, Instruction instruction) {
    return isConditionMet.test(result, instruction);
  }
}
