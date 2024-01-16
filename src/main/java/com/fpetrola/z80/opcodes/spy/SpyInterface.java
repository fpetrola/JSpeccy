package com.fpetrola.z80.opcodes.spy;

import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.Instruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

public interface SpyInterface {

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

}