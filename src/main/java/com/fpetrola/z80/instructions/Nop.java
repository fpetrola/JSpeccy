package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.instructions.base.AbstractInstruction;

public class Nop extends AbstractInstruction {
  public Nop(State state) {
    super(state);
  }

  public int execute() {
    return 4;
  }
}
