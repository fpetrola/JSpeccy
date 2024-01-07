package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Ldd extends AbstractOpCode {

  public Ldd(State state) {
    super(state);
  }

  public int execute() {
    hl.decrement(1);
    de.decrement(1);
    bc.decrement(1);
    flag.LDD(a.read(), hl.read(), bc.read());
    return 1;
  }
}
