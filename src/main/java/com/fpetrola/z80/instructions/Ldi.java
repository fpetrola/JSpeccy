package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;

public class Ldi extends AbstractInstruction {

  public Ldi(State state) {
    super(state);
  }

  public int execute() {
    spy.pause();
    int hlValue = hl.read();
    int deValue = de.read();
    int aValue = a.read();
    spy.doContinue();
    int work8 = memory.read(hlValue);
    memory.write(deValue, work8);
    spy.pause();

    hl.increment(1);
    de.increment(1);
    bc.decrement(1);

    flag.LDI(aValue, work8, bc.read());
    spy.doContinue();

    return 1;
  }
}
