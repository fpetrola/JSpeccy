package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.instructions.base.InstructionVisitor;

public interface Condition {
  boolean conditionMet();

  default void accept(InstructionVisitor visitor) {
  }
}
