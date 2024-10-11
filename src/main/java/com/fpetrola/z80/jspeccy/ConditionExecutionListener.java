package com.fpetrola.z80.jspeccy;

import com.fpetrola.z80.instructions.base.Instruction;

public interface ConditionExecutionListener {
  boolean executingCondition(Instruction<Boolean> instruction, boolean alwaysTrue, boolean state);
}
