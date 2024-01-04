package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Ldir extends AbstractOpCode {
  private Ldi ldi;

  public Ldir(State state, OpcodeTargets opt, OpcodeConditions opc) {
    super(state);
    ldi = new Ldi(state, opt);
  }

  public int execute() {
    int execute = ldi.execute();

    if (bc.read() != 0)
      pc.increment(-2);

    return execute;
  }
}
