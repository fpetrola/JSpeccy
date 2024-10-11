package com.fpetrola.z80.cpu;

import com.fpetrola.z80.instructions.base.Instruction;

public interface InstructionExecutor<T> {
  Instruction<T> execute(Instruction<T> instruction);

  boolean isExecuting(Instruction<T> instruction);

  default void reset() {
  }
}
