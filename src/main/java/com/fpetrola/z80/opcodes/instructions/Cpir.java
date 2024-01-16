package com.fpetrola.z80.opcodes.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.opcodes.references.AbstractInstruction;

public class Cpir extends AbstractInstruction {
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
