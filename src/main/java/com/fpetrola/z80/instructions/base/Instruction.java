package com.fpetrola.z80.instructions.base;

public interface Instruction {
  int execute();

  public int getLength();

  void incrementLengthBy(int by);

  Instruction getBaseInstruction();
}
