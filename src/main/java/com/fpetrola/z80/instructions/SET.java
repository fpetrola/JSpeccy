package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class SET extends BitOperation {

  public SET(State state, OpcodeReference target, int n, int valueDelta) {
    super(state, target, n, valueDelta);
  }

  public int execute() {
    final int value = target.read();
    final int bit = 1 << n;
    final int result = value | bit;
    target.write(result);

    return cyclesCost;
  }

  public String toString() {
    return "SET " + n + ", " + target;
  }
}
