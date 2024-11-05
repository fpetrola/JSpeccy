package com.fpetrola.z80.jspeccy;

import com.fpetrola.z80.instructions.base.Instruction;

public interface ConditionPredicate<T> {
  boolean test(T t, Instruction<T> instruction);
}
