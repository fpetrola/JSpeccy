package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.RepeatingInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;

public class Outdr<T extends WordNumber> extends RepeatingInstruction<T> {
  public Outdr(ImmutableOpcodeReference<T> pc, RegisterPair<T> bc, Outd outd) {
    super(outd, pc, bc);
  }
}
