package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.registers.Register;

public class Ini extends AbstractInstruction {

  public Ini(State state) {
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

    b.decrement(1);
    hl.increment(1);

    flag.INI(b.read());
    spy.doContinue();
    return 1;
  }
}
