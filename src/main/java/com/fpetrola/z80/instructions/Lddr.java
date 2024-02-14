package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Lddr<T extends WordNumber> extends RepeatingInstruction<T> {
  public Lddr(State state) {
    super(state, new Ldd(state));
  }

  protected boolean checkLoopCondition() {
    return bc.read().isNotZero();
  }
}
