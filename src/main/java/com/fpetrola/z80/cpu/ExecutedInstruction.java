package com.fpetrola.z80.cpu;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class ExecutedInstruction {
  private final int pc;
  private final Instruction<? extends WordNumber> instruction;

  public <T extends WordNumber> ExecutedInstruction(int pc, Instruction<T> instruction) {

    this.pc = pc;
    this.instruction = instruction;
  }
}
