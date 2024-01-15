package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Ld extends TargetSourceOpcode {

  public Ld(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target, source);
  }

  public int execute() {
    int value = source.read();
    target.write(value);

    return cyclesCost;
  }
}
