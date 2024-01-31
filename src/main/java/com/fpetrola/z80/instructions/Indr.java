package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.State;

public class Indr extends RepeatingInstruction {
  public Indr(State state) {
    super(state, new Ind(state));
  }

  protected boolean checkLoopCondition() {
    return b.read() != 0;
  }
}
