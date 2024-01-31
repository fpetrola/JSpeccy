package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.registers.Register;

public class Ind extends AbstractInstruction {

  public Ind(State state) {
    super(state);
  }

  public int execute() {
    spy.pause();

    int cValue = bc.getLow().read();
    int in = state.getIo().in(cValue);

    int hlValue = hl.read();
    spy.doContinue();

    memory.write(hlValue, in);

    spy.pause();

    Register b = bc.getHigh();
    b.decrement(1);
    hl.decrement(1);

    flag.IND(b.read());
    spy.doContinue();
    return 1;
  }
}
