package com.fpetrola.z80.instructions.old;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.old.PipeRegister;
import com.fpetrola.z80.opcodes.references.WordNumber;

public interface InstructionAdapter<T extends WordNumber> {
  Instruction<T> adapt(PipeRegister<T> register);
}
