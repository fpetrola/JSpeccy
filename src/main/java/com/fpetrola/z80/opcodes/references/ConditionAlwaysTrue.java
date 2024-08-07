package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.instructions.base.InstructionVisitor;

public class ConditionAlwaysTrue extends ConditionBase {

  public boolean conditionMet() {
    return filterCondition(true);
  }

  public void accept(InstructionVisitor visitor) {
    visitor.visitingConditionAlwaysTrue(this);
  }

  public String toString() {
    return "";
  }
}
