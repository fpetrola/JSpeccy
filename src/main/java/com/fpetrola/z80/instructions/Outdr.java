package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.State;

public class Outdr extends RepeatingInstruction {
  public Outdr(State state) {
    super(state, new Outd(state));
  }

  protected boolean checkLoopCondition() {
    return b.read() != 0;
  }
}
