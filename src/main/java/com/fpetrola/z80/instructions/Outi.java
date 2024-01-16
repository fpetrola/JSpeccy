package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;

public class Outi extends AbstractInstruction {
  public Outi(State state) {
    super(state);
  }

  public int execute() {
    int hlValue = hl.read();
    int valueFromHL = memory.read(hlValue);

    int cValue = bc.getLow().read();
    state.getIo().out(cValue, valueFromHL);

    hl.increment(1);
    bc.getHigh().decrement(1);

    return 1;
  }
}
