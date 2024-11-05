package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public interface FlagInstruction<T extends WordNumber> {
  Register<T> getFlag();
}
