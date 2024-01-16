package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.instructions.base.AbstractInstruction;

public class CCF extends AbstractInstruction {

  public CCF(State state) {
    super(state);
  }

  public int execute() {
    flag.CCF(a.read());
    return 4;
  }

}
