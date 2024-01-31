package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.State;

public class Inir extends RepeatingInstruction {
  public Inir(State state) {
    super(state, new Ini(state));
  }

  protected boolean checkLoopCondition() {
    return b.read() != 0;
  }
}
