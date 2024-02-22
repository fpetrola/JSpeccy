package com.fpetrola.z80.instructions;

import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public class Indr<T extends WordNumber> extends RepeatingInstruction<T> {
  Indr(ImmutableOpcodeReference<T> pc, Register<T> b, Register<T> bc, Ind ind) {
    super(ind, pc, b, bc);
  }
}
