package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.RepeatingInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public class Ldir<T extends WordNumber> extends RepeatingInstruction<T> {
  public Ldir(ImmutableOpcodeReference<T> pc, Register<T> b, Register<T> bc, Ldi ldi) {
    super(ldi, pc, b, bc);
  }

  protected boolean checkLoopCondition() {
    return bc.read().isNotZero();
  }
}
