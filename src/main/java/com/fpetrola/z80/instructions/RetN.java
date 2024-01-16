package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.opcodes.models.Condition;

public class RetN extends Ret {

  public RetN(State state, Condition condition) {
    super(state, condition);
  }

  public int execute() {
    state.setIff1(state.isIff2());
    return super.execute();
  }
}
