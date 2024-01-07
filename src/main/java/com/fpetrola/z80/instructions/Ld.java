package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Ld extends AbstractOpCode {
  
  public Ld(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target, source);
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
