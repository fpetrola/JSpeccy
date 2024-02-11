package com.fpetrola.z80.spy;

import java.util.function.Supplier;

import com.fpetrola.z80.blocks.references.ReferencesHandler;
import com.fpetrola.z80.cpu.OOZ80;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.ExecutionPoint;
import com.fpetrola.z80.opcodes.references.MemoryPlusRegister8BitReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

public class NullInstructionSpy<T extends WordNumber> implements InstructionSpy<T> {

  @Override
  public boolean isReadAccessCapture() {
    return false;
  }

  @Override
  public long getExecutionNumber() {
    return 0;
  }

  @Override
  public boolean[] getBitsWritten() {
    return new boolean[0];
  }

  @Override
  public boolean wasFetched(int address) {
    return false;
  }

  @Override
  public boolean isIndirectReference() {
    return false;
  }

  public boolean isCapturing() {
    return false;
  }

  public Memory<T> wrapMemory(Memory<T> aMemory) {
    return aMemory;
  }

  public OpcodeReference<T> wrapOpcodeReference(OpcodeReference<T> opcodeReference) {
    return opcodeReference;
  }

  public Register wrapOpcodeRegister(Register register, RegisterName name) {
    return register;
  }

  @Override
  public void start(Instruction<T> opcode, int opcodeInt, T pcValue) {

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
  public void flipOpcode(Instruction<T> instruction, int opcodeInt) {

  }

  @Override
  public MemoryPlusRegister8BitReference wrapMemoryPlusRegister8BitReference(MemoryPlusRegister8BitReference memoryPlusRegister8BitReference) {
    return memoryPlusRegister8BitReference;
  }

  @Override
  public void addWriteReference(RegisterName opcodeReference, T value, boolean isIncrement) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void addReadReference(RegisterName opcodeReference, T value) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void addWriteMemoryReference(T address, T value) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void addReadMemoryReference(T address, T value) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean isStructureCapture() {
    return false;
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
  public ReferencesHandler enableStructureCapture() {
    return null;
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
    return object.get();
  }

  @Override
  public void setSecondZ80(OOZ80 z802) {

  }

  @Override
  public ExecutionPoint getLastExecutionPoint() {
    return null;
  }

  @Override
  public void export() {

  }

  @Override
  public void enableReadAccessCapture() {

  }

}
