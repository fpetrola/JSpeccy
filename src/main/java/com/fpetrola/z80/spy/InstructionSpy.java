package com.fpetrola.z80.spy;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.MemoryPlusRegister8BitReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

public interface InstructionSpy {

  boolean isCapturing();

  Memory wrapMemory(Memory aMemory);

  OpcodeReference wrapOpcodeReference(OpcodeReference opcodeReference);

  Register wrapOpcodeRegister(Register register, RegisterName name);

  void start(Instruction opcode, int opcodeInt, int pcValue);

  void end();

  void enable(boolean enabled);

  void setSpritesArray(boolean[] bitsWritten);

  void undo();

  public void flipOpcode(Instruction instruction, int opcodeInt);

  MemoryPlusRegister8BitReference wrapMemoryPlusRegister8BitReference(MemoryPlusRegister8BitReference memoryPlusRegister8BitReference);

}