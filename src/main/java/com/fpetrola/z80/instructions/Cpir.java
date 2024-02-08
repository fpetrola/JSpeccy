package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Cpir<T extends WordNumber> extends RepeatingInstruction<T> {
  public Cpir(State state) {
    super(state, new Cpi(state));
  }

  protected boolean checkLoopCondition() {
    return !state.isZ() && bc.read().notEquals(0);
  }
}
