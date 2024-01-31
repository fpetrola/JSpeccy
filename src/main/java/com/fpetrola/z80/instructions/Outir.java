package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.State;

public class Outir extends RepeatingInstruction {
  public Outir(State state) {
    super(state, new Outi(state));
  }

  protected boolean checkLoopCondition() {
    return b.read() != 0;
  }
}
