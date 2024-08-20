package com.fpetrola.z80.jspeccy;

import com.fpetrola.z80.instructions.base.Instruction;

import java.util.function.Predicate;

public class FlipFLopConditionFlag {
  public final FlipFlopPredicate isConditionMet;

  public ConditionExecutionListener getExecutionsListener() {
    return isConditionMet.executionsListener;
  }


  public FlipFLopConditionFlag(ConditionExecutionListener executionListener, boolean alwaysTrue) {
    isConditionMet = new FlipFlopPredicate(executionListener, alwaysTrue);
  }

  public class FlipFlopPredicate implements ConditionPredicate<Boolean> {
    public boolean state = false;
    public ConditionExecutionListener executionsListener;
    public boolean alwaysTrue;

    public FlipFlopPredicate(ConditionExecutionListener executionsListener, boolean alwaysTrue) {
      this.executionsListener = executionsListener;
      this.alwaysTrue = alwaysTrue;
    }

    @Override
    public boolean test(Boolean aBoolean, Instruction<Boolean> instruction) {
      boolean result = state;
      state = !state;
//    return Math.random() * 100 > 50;
      result = alwaysTrue || result;
      result = executionsListener.executingCondition(instruction, alwaysTrue, result);
      return result;
    }
  }
}
