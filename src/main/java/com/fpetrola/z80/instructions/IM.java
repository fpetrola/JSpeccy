package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.State.IntMode2;

public class IM extends AbstractOpCode {

  private int i;

  public IM(State state, int i) {
    super(state);
    this.i = i;
  }

  @Override
  public int execute() {
    state.setIntMode(IntMode2.values()[i]);
    return 4;
  }

  public String toString() {
    return "IM" + i;
  }
}
