package com.fpetrola.z80.cpu;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;

public interface Z80Cpu<T extends WordNumber> {
  void reset();

  void execute();

  void interruption();

  void endInterruption();

  InstructionFetcher getInstructionFetcher();

  State<T> getState();
}
