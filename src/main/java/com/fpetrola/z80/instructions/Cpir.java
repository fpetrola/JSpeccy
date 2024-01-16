package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Cpir extends AbstractOpCode {
  public Cpir(State state) {
    super(state);
  }

  public int execute() {
    Cpi cpi = new Cpi(state);
    int execute = cpi.execute();

    if (!state.isZ() && bc.read() != 0)
      state.setNextPC(pc.read());

    return execute;
  }
}
