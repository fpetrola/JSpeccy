package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.opcodes.models.Condition;

public class ConditionAlwaysTrue implements Condition {

  public boolean conditionMet() {
    return true;
  }

  public String toString() {
    return "";
  }
}
