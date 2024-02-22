package com.fpetrola.z80.instructions;

import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public class Ldir<T extends WordNumber> extends RepeatingInstruction<T> {
  Ldir(ImmutableOpcodeReference<T> pc, Register<T> b, Register<T> bc) {
    super(InstructionFactory.createLdi(), pc, b, bc);
  }

  protected boolean checkLoopCondition() {
    return bc.read().isNotZero();
  }
}
