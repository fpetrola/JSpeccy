package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.spy.InstructionSpy;

public interface Instruction {
  int execute();

  public int getLength();

  void incrementLengthBy(int by);

  Instruction getBaseInstruction();

  void setSpy(InstructionSpy spy);
   int getNextPC();
}
