package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class DJNZ extends AbstractOpCode {

  private final OpcodeReference source;

  public DJNZ(State state, OpcodeReference source) {
    super(state);
    this.source = source;
  }

  public int execute() {
    b.decrement(1);

    int counter = b.read();

    if (counter != 0) {
      pc.increment(1);
      pc.increment((byte) source.read());
      return 5 + 1 + source.cyclesCost() + 5;
    } else
      pc.increment(2);

    return 5 + 1 + source.cyclesCost();
  }

  @Override
  public String toString() {
    return "DJNZ " + source.toString();
  }

}
