package com.fpetrola.z80.jspeccy;

import java.util.function.Predicate;

public class FlipFLopConditionFlag {
  public final FlipFlopPredicate isConditionMet;

  public ConditionExecutionListener getExecutionsListener() {
    return isConditionMet.executionsListener;
  }


  public FlipFLopConditionFlag(ConditionExecutionListener executionsListener1, boolean alwaysTrue) {
    isConditionMet = new FlipFlopPredicate(executionsListener1, alwaysTrue);
  }

  public class FlipFlopPredicate implements Predicate<Boolean> {
    public boolean state = false;
    public ConditionExecutionListener executionsListener;
    public boolean alwaysTrue;

    public FlipFlopPredicate(ConditionExecutionListener executionsListener, boolean alwaysTrue) {
      this.executionsListener = executionsListener;
      this.alwaysTrue = alwaysTrue;
    }

    public boolean test(Boolean b) {
      boolean result = state;
      state = !state;
//    return Math.random() * 100 > 50;
      executionsListener.executingCondition(alwaysTrue, result);
      return alwaysTrue || result;
    }
  }
}
