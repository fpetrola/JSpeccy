package com.fpetrola.z80;

import com.fpetrola.z80.instructions.OpCode;
import com.fpetrola.z80.instructions.OpcodeReference;
import com.fpetrola.z80.instructions.SpyInterface;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

public class NullSpy implements SpyInterface{

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
    // TODO Auto-generated method stub
    
  }

  @Override
  public void end() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void enable(boolean enabled) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setSpritesArray(boolean[] bitsWritten) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void undo() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void flipOpcode(OpCode opCode, int opcodeInt) {
    // TODO Auto-generated method stub
    
  }

}
