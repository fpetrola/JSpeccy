package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public class Lddr<T extends WordNumber> extends RepeatingInstruction<T> {
  Lddr(State state, ImmutableOpcodeReference<T> pc, Register<T> b, Register<T> bc) {
    super(new Ldd(state), pc, b, bc);
  }

  protected boolean checkLoopCondition() {
    return bc.read().isNotZero();
  }
}
