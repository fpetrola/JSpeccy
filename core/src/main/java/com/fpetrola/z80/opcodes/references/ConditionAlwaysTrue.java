package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.InstructionVisitor;

public class ConditionAlwaysTrue extends ConditionBase {

  public boolean conditionMet(Instruction instruction) {
    return filterCondition(true, instruction);
  }

  public void accept(InstructionVisitor visitor) {
    visitor.visitingConditionAlwaysTrue(this);
  }

  public String toString() {
    return "";
  }
}
