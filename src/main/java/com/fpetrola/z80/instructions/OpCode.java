package com.fpetrola.z80.instructions;

import com.fpetrola.z80.registers.Plain16BitRegister;

public interface OpCode {

  /**
   * Execute OpCode from current PC position and return the number of cycles
   *
   * @return number of cycles used to execute this opcode
   */
  int execute();

  public int getLength();

  void incrementLength();

  Plain16BitRegister getPC();

  void setPC(Plain16BitRegister pc);

  int getBasePc();

  public void setBasePc(int basePc);

  OpCode getInstruction();
}
