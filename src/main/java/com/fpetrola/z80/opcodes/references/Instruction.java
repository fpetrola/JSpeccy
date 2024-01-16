package com.fpetrola.z80.opcodes.references;

public interface Instruction {
  int execute();

  public int getLength();

  void incrementLengthBy(int by);

  Instruction getBaseInstruction();
}
