package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.instructions.base.AbstractInstruction;

public class EI extends AbstractInstruction {

  public EI(State state) {
    super(state);
  }

  public int execute() {
    state.enableInterrupt();
    return 4;
  }
}
