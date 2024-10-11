package com.fpetrola.z80.opcodes.decoder;

import com.fpetrola.z80.instructions.base.Instruction;

public interface FetchNextOpcodeInstruction<T> extends Instruction<T> {
  int getIncPc();
  Instruction[] getTable();
}