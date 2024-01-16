package com.fpetrola.z80;

import com.fpetrola.z80.instructions.OpCode;
import com.fpetrola.z80.instructions.OpcodeReference;
import com.fpetrola.z80.instructions.SpyInterface;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

public class NullSpy implements SpyInterface {

  public boolean isCapturing() {
    return false;
  }

  public Memory wrapMemory(Memory aMemory) {
    return aMemory;
  }

  public OpcodeReference wrapOpcodeReference(OpcodeReference opcodeReference) {
    return opcodeReference;
  }

  public Register wrapOpcodeRegister(Register register, RegisterName name) {
    return register;
  }

  @Override
  public void start(OpCode opcode, int opcodeInt, int pcValue) {

  }

  @Override
  public void end() {

  }

  @Override
  public void enable(boolean enabled) {

  }

  @Override
  public void setSpritesArray(boolean[] bitsWritten) {

  }

  @Override
  public void undo() {

  }

  @Override
  public void flipOpcode(OpCode opCode, int opcodeInt) {

  }

}
