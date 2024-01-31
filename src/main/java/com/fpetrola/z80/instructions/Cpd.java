package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;

public class Cpd extends AbstractInstruction {

  public Cpd(State state) {
    super(state);
  }

  public int execute() {
    int hlValue = hl.read();
    int valueFromHL = memory.read(hlValue);
    hl.decrement(1);
    bc.decrement(1);

    flag.CPD(valueFromHL, a.read(), bc.read());

    return 1;
  }
}
