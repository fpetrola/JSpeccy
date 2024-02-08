package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.spy.InstructionSpy;

public interface Instruction<T> {
  int execute();

  public int getLength();

  void incrementLengthBy(int by);

  Instruction<T> getBaseInstruction();

  void setSpy(InstructionSpy spy);
   T getNextPC();

  State getState();
}
