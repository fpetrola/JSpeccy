package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class CCF extends AbstractOpCode {

  public CCF(State state) {
    super(state);
  }

  public int execute() {
    pc.increment(1);
    flag.CCF(a.read());
    return 4;
  }

}
