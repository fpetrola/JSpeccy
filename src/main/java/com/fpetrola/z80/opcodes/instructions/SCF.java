package com.fpetrola.z80.opcodes.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.opcodes.references.AbstractInstruction;

public class SCF extends AbstractInstruction {

  public SCF(State state) {
    super(state);
  }

  public int execute() {
    flag.SCF(this.a.read());
    return 4;
  }

}
