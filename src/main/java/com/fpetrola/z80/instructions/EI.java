package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;

public class EI extends AbstractInstruction {

  public EI(State state) {
    super(state);
  }

  public int execute() {
    state.enableInterrupt();
    return 4;
  }
}
