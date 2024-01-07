package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class EI extends AbstractOpCode {

  public EI(State state) {
    super(state);
  }

  public int execute() {
    state.enableInterrupt();
    return 4;
  }
}
