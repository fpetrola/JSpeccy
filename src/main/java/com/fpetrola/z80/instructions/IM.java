package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.State.OOIntMode;
import com.fpetrola.z80.instructions.base.AbstractInstruction;

public class IM extends AbstractInstruction {
  int mode;

  public IM(State state, int mode) {
    super(state);
    this.mode = mode;
  }

  public int execute() {
    state.setIntMode(OOIntMode.values()[mode]);
    return 4;
  }

  public String toString() {
    return "IM" + mode;
  }

  public int getMode() {
    return mode;
  }
}
