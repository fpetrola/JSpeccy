package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;

public class Nop extends AbstractInstruction {
  public Nop(State state) {
    super(state);
  }

  public int execute() {
    return 4;
  }
}
