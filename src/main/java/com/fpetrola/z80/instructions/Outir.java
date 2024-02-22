package com.fpetrola.z80.instructions;

import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public class Outir<T extends WordNumber> extends RepeatingInstruction<T> {
  Outir(ImmutableOpcodeReference<T> pc, Register<T> b, Register<T> bc) {
    super(InstructionFactory.createOuti(), pc, b, bc);
  }
}
