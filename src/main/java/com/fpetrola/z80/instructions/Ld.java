package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Ld extends AbstractOpCode {
  private final OpcodeReference target;
  private final OpcodeReference source;

  public Ld(State state, OpcodeReference target, OpcodeReference source) {
    super(state);
    this.target = target;
    this.source = source;
  }

  public int execute() {
    pc.increment(1);
    int value = source.read();
    target.write(value);

    return 4 + target.cyclesCost() + source.cyclesCost();
  }

  public String toString() {
    return "LD " + target + "," + source;
  }
}
