package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Cpdr<T extends WordNumber> extends RepeatingInstruction<T> {
  public Cpdr(State state) {
    super(state, new Cpd(state));
  }

  protected boolean checkLoopCondition() {
    return !state.isZ() && bc.read().isNotZero();
  }
}
