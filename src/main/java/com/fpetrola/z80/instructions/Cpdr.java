package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.State;

public class Cpdr extends RepeatingInstruction {
  public Cpdr(State state) {
    super(state, new Cpd(state));
  }
  protected boolean checkLoopCondition() {
    return !state.isZ() && bc.read() != 0;
  }
}
