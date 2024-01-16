package com.fpetrola.z80.opcodes.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.opcodes.references.AbstractInstruction;

public class DI extends AbstractInstruction {

  public DI(State state) {
    super(state);
  }

  public int execute() {
    state.resetInterrupt();
    return 4;
  }
}
