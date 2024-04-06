package com.fpetrola.z80.cpu;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.WordNumber;

public interface InstructionExecutor<T> {
  void execute(Instruction<T> instruction);

  boolean isExecuting(Instruction<T> instruction);
}
