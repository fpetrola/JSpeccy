package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Ini extends AbstractOpCode {

  public Ini(State state) {
    super(state);
  }

  public int execute() {
    int cValue = bc.getLow().read();
    int in = state.getIo().in(cValue);

    int hlValue = hl.read();
    memory.write(hlValue, in);

    hl.increment(1);
    bc.getHigh().decrement(1);

    return 1;
  }
}
