package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.registers.Flags;

public class BIT extends AbstractOpCode {

  private final OpcodeReference target;
  private final int n;
  private int valueDelta;

  public BIT(State state, OpcodeReference target, int n, int valueDelta) {
    super(state);
    this.target = target;
    this.n = n;
    this.valueDelta = valueDelta;
  }

  @Override
  public int execute() {

    pc.increment(valueDelta);

    pc.increment(1);

    final int value = target.read();

    flag.testBit(value, n);
    
    if (valueDelta != 0)
      pc.increment(1);

    return 4 + target.cyclesCost() + target.cyclesCost();
  }

  @Override
  public String toString() {
    return "BIT " + n + ", " + target;
  }

}
