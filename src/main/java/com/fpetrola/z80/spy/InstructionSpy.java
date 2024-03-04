package com.fpetrola.z80.spy;

import java.util.function.Supplier;

import com.fpetrola.z80.cpu.Z80Cpu;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.ExecutionPoint;
import com.fpetrola.z80.opcodes.references.MemoryPlusRegister8BitReference;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

public interface InstructionSpy<T> {

  boolean isReadAccessCapture();

  long getExecutionNumber();

  boolean[] getBitsWritten();

  Instruction getFetchedAt(int address);

  boolean wasFetched(int address);

  boolean isIndirectReference();

  boolean isCapturing();

  Memory<T> wrapMemory(Memory<T> aMemory);

  ImmutableOpcodeReference<T> wrapOpcodeReference(ImmutableOpcodeReference<T> immutableOpcodeReference);

  Register<T> wrapOpcodeRegister(Register<T> register);

  void beforeExecution(Instruction<T> opcode);

  void afterExecution(Instruction<T> instruction);

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

  void enableStructureCapture();

  void setState(State state);

  void switchToIndirectReference();

  void switchToDirectReference();

  <T> T executeInPause(Supplier<T> object);

  void setSecondZ80(Z80Cpu z802);

  ExecutionPoint getLastExecutionPoint();

  void export();

  void enableReadAccessCapture();

  void setGameName(String gameName);
}