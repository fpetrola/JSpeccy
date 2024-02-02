package com.fpetrola.z80.spy;

import java.util.function.Supplier;

import com.fpetrola.z80.cpu.OOZ80;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.MemoryPlusRegister8BitReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

public class NullInstructionSpy implements InstructionSpy {

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
  public void start(Instruction opcode, int opcodeInt, int pcValue) {

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
  public void flipOpcode(Instruction instruction, int opcodeInt) {

  }

  @Override
  public MemoryPlusRegister8BitReference wrapMemoryPlusRegister8BitReference(MemoryPlusRegister8BitReference memoryPlusRegister8BitReference) {
    return memoryPlusRegister8BitReference;
  }

  @Override
  public void addWriteReference(RegisterName opcodeReference, int value, boolean isIncrement) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void addReadReference(RegisterName opcodeReference, int value) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void addWriteMemoryReference(int address, int value) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void addReadMemoryReference(int address, int value) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void reset(State state) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void pause() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void doContinue() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setState(State state) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void switchToIndirectReference() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void switchToDirectReference() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public <T> T executeInPause(Supplier<T> object) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setSecondZ80(OOZ80 z802) {

  }

}
