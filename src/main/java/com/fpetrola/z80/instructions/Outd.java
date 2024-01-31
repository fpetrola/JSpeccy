package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.registers.Register;

public class Outd extends AbstractInstruction {
  public Outd(State state) {
    super(state);
  }

  public int execute() {

    spy.pause();

    int hlValue = hl.read();
    int valueFromHL = memory.read(hlValue);

    int cValue = bc.getLow().read();
    spy.doContinue();

    state.getIo().out(cValue, valueFromHL);

    spy.pause();

    hl.decrement(1);
    Register b = bc.getHigh();
    b.decrement(1);

    flag.OUTD(b.read());

    spy.doContinue();

    return 1;
  }
}
