package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;

public class DI extends AbstractInstruction {

  public DI(State state) {
    super(state);
  }

  public int execute() {
    state.resetInterrupt();
    return 4;
  }
}
