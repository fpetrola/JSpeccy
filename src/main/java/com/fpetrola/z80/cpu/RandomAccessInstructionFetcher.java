package com.fpetrola.z80.cpu;

import com.fpetrola.z80.instructions.base.Instruction;

public interface RandomAccessInstructionFetcher<T> {
  Instruction<T> getInstructionAt(int address);
}
