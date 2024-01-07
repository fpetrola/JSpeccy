package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Nop extends AbstractOpCode {
  public Nop(State state) {
    super(state);
  }

  public int execute() {
    return 4;
  }
}
