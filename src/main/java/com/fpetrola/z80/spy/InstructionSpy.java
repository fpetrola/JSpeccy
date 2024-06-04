package com.fpetrola.z80.spy;

import com.fpetrola.z80.cpu.Z80Cpu;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.ExecutionPoint;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.MemoryPlusRegister8BitReference;
import com.fpetrola.z80.registers.Register;

import java.util.function.Supplier;

public interface InstructionSpy<T> {

  default boolean isReadAccessCapture() {
    return false;
  }

  default long getExecutionNumber() {
    return 0;
  }

  default boolean[] getBitsWritten() {
    return new boolean[0];
  }


  default Instruction getFetchedAt(int address) {
    return null;
  }


  default boolean wasFetched(int address) {
    return false;
  }


  default boolean isIndirectReference() {
    return false;
  }

  default boolean isCapturing() {
    return false;
  }

  default Memory<T> wrapMemory(Memory<T> aMemory) {
    return aMemory;
  }

  default ImmutableOpcodeReference<T> wrapOpcodeReference(ImmutableOpcodeReference<T> immutableOpcodeReference) {
    return immutableOpcodeReference;
  }

  default Register<T> wrapRegister(Register<T> register) {
    return register;
  }


  default void beforeExecution(Instruction<T> opcode) {

  }


  default void afterExecution(Instruction<T> instruction) {

  }


  default void enable(boolean enabled) {

  }


  default void setSpritesArray(boolean[] bitsWritten) {

  }


  default void undo() {

  }


  default void flipOpcode(Instruction<T> instruction, int opcodeInt) {

  }


  default MemoryPlusRegister8BitReference wrapMemoryPlusRegister8BitReference(MemoryPlusRegister8BitReference memoryPlusRegister8BitReference) {
    return memoryPlusRegister8BitReference;
  }


  default void addWriteReference(String opcodeReference, T value, boolean isIncrement) {
  }


  default void addReadReference(String opcodeReference, T value) {
  }


  default void addWriteMemoryReference(T address, T value) {
  }


  default void addReadMemoryReference(T address, T value) {
  }


  default boolean isStructureCapture() {
    return false;
  }


  default void reset(State state) {
  }


  default void pause() {
  }


  default void doContinue() {
  }


  default void enableStructureCapture() {
  }


  default void setState(State state) {
  }


  default void switchToIndirectReference() {
  }


  default void switchToDirectReference() {
  }


  default <T> T executeInPause(Supplier<T> object) {
    return object.get();
  }


  default void setSecondZ80(Z80Cpu z802) {

  }


  default ExecutionPoint getLastExecutionPoint() {
    return null;
  }


  default void export() {

  }


  default void enableReadAccessCapture() {

  }


  default void setGameName(String gameName) {

  }
}