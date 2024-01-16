package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;

public class SCF extends AbstractInstruction {

  public SCF(State state) {
    super(state);
  }

  public int execute() {
    flag.SCF(this.a.read());
    return 4;
  }

}
