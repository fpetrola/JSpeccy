package com.fpetrola.z80.opcodes.references;

public class ConditionAlwaysTrue implements Condition {

  public boolean conditionMet() {
    return true;
  }

  public String toString() {
    return "";
  }
}
