package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class SET extends TargetOpCode {

  private final int n;
  private int valueDelta;

  public SET(State state, OpcodeReference target, int n, int valueDelta) {
    super(state, target);
    this.n = n;
    this.valueDelta = valueDelta;
  }

  @Override
  public int execute() {
    pc.increment(valueDelta);

    final int value = target.read();
    final int bit = 1 << n;
    final int result = value | bit;
    target.write(result);

    if (valueDelta != 0)
      pc.increment(1);

    return 4 + target.cyclesCost() + target.cyclesCost();
  }

  @Override
  public String toString() {
    return "SET " + n + ", " + target;
  }

}
