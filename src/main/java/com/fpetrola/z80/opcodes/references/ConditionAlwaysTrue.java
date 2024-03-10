package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.instructions.base.InstructionVisitor;

public class ConditionAlwaysTrue implements Condition {

  public boolean conditionMet() {
    return true;
  }

  public void accept(InstructionVisitor visitor) {
    visitor.visitingConditionAlwaysTrue(this);
  }

  public String toString() {
    return "";
  }
}
