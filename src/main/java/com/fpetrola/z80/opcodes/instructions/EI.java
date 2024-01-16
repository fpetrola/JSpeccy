package com.fpetrola.z80.opcodes.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.opcodes.references.AbstractInstruction;

public class EI extends AbstractInstruction {

  public EI(State state) {
    super(state);
  }

  public int execute() {
    state.enableInterrupt();
    return 4;
  }
}
