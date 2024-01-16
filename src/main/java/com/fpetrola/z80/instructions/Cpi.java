package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;

public class Cpi extends AbstractInstruction {

  public Cpi(State state) {
    super(state);
  }

  public int execute() {
    int hlValue = hl.read();
    int valueFromHL = memory.read(hlValue);

    flag.CPI(valueFromHL, a.read(), bc.read());

    hl.increment(1);
    bc.decrement(1);

    return 1;
  }
}
