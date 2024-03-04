package com.fpetrola.z80.cpu;

import com.fpetrola.z80.instructions.base.Instruction;

public interface InstructionExecutor<T> {
  void execute(Instruction<T> instruction);
}
