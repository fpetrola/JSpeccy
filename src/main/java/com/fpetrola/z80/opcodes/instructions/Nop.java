package com.fpetrola.z80.opcodes.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.opcodes.references.AbstractInstruction;

public class Nop extends AbstractInstruction {
  public Nop(State state) {
    super(state);
  }

  public int execute() {
    return 4;
  }
}
