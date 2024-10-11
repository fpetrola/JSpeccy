package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.RepeatingInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;

public class Inir<T extends WordNumber> extends RepeatingInstruction<T> {
  public Inir(ImmutableOpcodeReference<T> pc, RegisterPair<T> bc, Ini ini) {
    super(ini, pc, bc);
  }
}
