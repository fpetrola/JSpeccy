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
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

public interface InstructionSpy<T> {

  long getExecutionNumber();

  Instruction getInstruction();

  boolean[] getBitsWritten();

  boolean wasFetched(int address);

  boolean isIndirectReference();

  boolean isCapturing();

  Memory<T> wrapMemory(Memory<T> aMemory);

  OpcodeReference<T> wrapOpcodeReference(OpcodeReference<T> opcodeReference);

  Register<T> wrapOpcodeRegister(Register<T> register, RegisterName name);

  void start(Instruction<T> opcode, int opcodeInt, T pcValue);

  void end();

  void enable(boolean enabled);

  void setSpritesArray(boolean[] bitsWritten);

  void undo();

  public void flipOpcode(Instruction<T> instruction, int opcodeInt);

  MemoryPlusRegister8BitReference wrapMemoryPlusRegister8BitReference(MemoryPlusRegister8BitReference memoryPlusRegister8BitReference);

  void addWriteReference(RegisterName opcodeReference, T value, boolean isIncrement);

  void addReadReference(RegisterName opcodeReference, T value);

  void addWriteMemoryReference(T address, T value);

  void addReadMemoryReference(T address, T value);

  boolean isStructureCapture();

  void reset(State state);

  void pause();

  void doContinue();

  ReferencesHandler enableStructureCapture();

  void setState(State state);

  void switchToIndirectReference();

  void switchToDirectReference();

  <T> T executeInPause(Supplier<T> object);

  void setSecondZ80(OOZ80 z802);

  int getPc();

  ExecutionPoint getLastExecutionPoint();

  void export();
}