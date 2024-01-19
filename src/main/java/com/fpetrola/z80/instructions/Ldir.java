package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;

public class Ldir extends AbstractInstruction {
  private Ldi ldi;

  public Ldir(State state) {
    super(state);
    ldi = new Ldi(state);
  }

  public int execute() {
    ldi.setSpy(spy);
    int execute = ldi.execute();
    
    spy.pause();

    boolean notFinished = bc.read() != 0 && (a.read() & 0xff) != (hl.read() & 0xffff);
    if (notFinished)
      state.setNextPC(pc.read());
    
    spy.doContinue();

    return execute;
  }

}
