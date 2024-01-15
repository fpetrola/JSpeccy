package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class BIT extends BitOperation {
  public BIT(State state, OpcodeReference target, int n, int valueDelta) {
    super(state, target, n, valueDelta);
  }

  public int execute() {
    final int value = target.read();
    flag.testBit(value, n);

    return cyclesCost;
  }

  public String toString() {
    return "BIT " + n + ", " + target;
  }
}
