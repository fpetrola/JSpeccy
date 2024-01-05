package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Cpir extends AbstractOpCode {

  private OpcodeTargets opt;
  private OpcodeConditions opc;

  public Cpir(State state, OpcodeTargets opt, OpcodeConditions opc) {
    super(state);
    this.opt = opt;
    this.opc = opc;
  }

  public int execute() {
    Cpi cpi = new Cpi(state);
    int execute = cpi.execute();

    if (!state.isZ() && bc.read() != 0)
      pc.increment(-2);

    return execute;
  }
}
