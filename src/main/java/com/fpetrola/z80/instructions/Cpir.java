package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Cpir extends AbstractOpCode {

  private OpcodeTargets opt;

  public Cpir(State state, OpcodeTargets opt) {
    super(state);
    this.opt = opt;
  }

  public int execute() {
    Cpi cpi = new Cpi(state, opt);
    int execute = cpi.execute();

    if (!state.isZ() && bc.read() != 0)
      pc.increment(-2);

    return execute;
  }
}
