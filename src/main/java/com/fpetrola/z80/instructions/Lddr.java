package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Lddr extends AbstractOpCode {

  public Lddr(State state) {
    super(state);
  }

  public int execute() {
    Ldd ldd = new Ldd(state);
    int execute = ldd.execute();

    if (bc.read() != 0)
      state.setNextPC(pc.read());

    return execute;
  }
}
