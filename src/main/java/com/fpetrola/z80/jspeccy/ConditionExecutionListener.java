package com.fpetrola.z80.jspeccy;

public interface ConditionExecutionListener {
  void executingCondition(boolean alwaysTrue, boolean state);
}
