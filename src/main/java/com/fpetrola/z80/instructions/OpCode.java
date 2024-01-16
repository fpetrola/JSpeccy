package com.fpetrola.z80.instructions;

public interface OpCode {

  /**
   * Execute OpCode from current PC position and return the number of cycles
   *
   * @return number of cycles used to execute this opcode
   */
  int execute();

  public int getLength();

  void incrementLengthBy(int by);

  OpCode getInstruction();
}
