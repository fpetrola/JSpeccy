package com.fpetrola.z80.cpu;

import com.fpetrola.z80.instructions.Ld;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.WordNumber;

public interface InstructionFetcher<T extends WordNumber> {
  int getOpcodeInt();

  void fetchNextInstruction(InstructionExecutor<T> instructionExecutor);
  void reset();
}
