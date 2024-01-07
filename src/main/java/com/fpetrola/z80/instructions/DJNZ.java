package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class DJNZ extends TargetOpCode {

  public DJNZ(State state, OpcodeReference source) {
    super(state, source);
  }

  public int execute() {
    b.decrement(1);

    int counter = b.read();

    if (counter != 0) {
      pc.increment((byte) target.read());
      return 5 + 1 + target.cyclesCost() + 5;
    }
    else
      pc.increment(1);
    return 5 + 1 + target.cyclesCost();
  }

  @Override
  public String toString() {
    return "DJNZ " + target.toString();
  }
}
