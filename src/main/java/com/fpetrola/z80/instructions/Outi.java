package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Outi extends AbstractOpCode {

  public Outi(State state, OpcodeTargets opt) {
    super(state);
  }

  public int execute() {

    int hlValue = hl.read();
    int valueFromHL = memory.read(hlValue);

    int cValue = bc.getLow().read();
    state.getIo().out(cValue, valueFromHL);

    hl.increment(1);
    bc.getHigh().decrement(1);

    pc.increment(1);

    return 1;
  }
}
