package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class SET extends BitOperation {

  public SET(State state, OpcodeReference target, int n, int valueDelta) {
    super(state, target, n, valueDelta);
  }

  public int execute() {
    target.write(target.read() | 1 << n);
    return cyclesCost;
  }
}
