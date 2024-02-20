package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.registers.Register;

public interface RegisterPairAdapter<T> {
  Instruction<T> adapt(Register<T> register1, Register<T> register2);
}
