package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.InstructionVisitor;

public interface Condition {
  boolean conditionMet(Instruction instruction);

  default void accept(InstructionVisitor visitor) {
  }
}
