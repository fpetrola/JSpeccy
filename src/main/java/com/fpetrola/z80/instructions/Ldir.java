package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Ldir extends AbstractOpCode {
  private Ldi ldi;

  public Ldir(State state) {
    super(state);
    ldi = new Ldi(state);
  }

  public int execute() {
    int execute = ldi.execute();

    if (bc.read() != 0 && (a.read() & 0xff) != (hl.read() & 0xffff))
      state.setNextPC(pc.read());

    return execute;
  }

}
