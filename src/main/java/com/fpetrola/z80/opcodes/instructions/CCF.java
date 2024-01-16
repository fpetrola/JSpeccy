package com.fpetrola.z80.opcodes.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.opcodes.references.AbstractInstruction;

public class CCF extends AbstractInstruction {

  public CCF(State state) {
    super(state);
  }

  public int execute() {
    flag.CCF(a.read());
    return 4;
  }

}
