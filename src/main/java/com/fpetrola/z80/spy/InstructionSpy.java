package com.fpetrola.z80.spy;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.MemoryPlusRegister8BitReference;
import com.fpetrola.z80.registers.Register;

public interface InstructionSpy<T> {
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

  default void flipOpcode(Instruction<T> instruction, int opcodeInt) {

  }

  default MemoryPlusRegister8BitReference wrapMemoryPlusRegister8BitReference(MemoryPlusRegister8BitReference memoryPlusRegister8BitReference) {
    return memoryPlusRegister8BitReference;
  }

  default void reset(State state) {
  }

  default void pause() {
  }

  default void doContinue() {
  }

  default void setState(State state) {
  }

  default boolean isCapturing() {
    return false;
  }
}
