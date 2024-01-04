package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class RES extends AbstractOpCode {

  private final OpcodeReference target;
  private final int n;
  private int valueDelta;

  public RES(State state, OpcodeReference target, int n, int valueDelta) {
    super(state);
    this.target = target;
    this.n = n;
    this.valueDelta = valueDelta;
  }

  @Override
  public int execute() {

//        incrementPC();
//
//        final int value = target.read();
//        final int bit = 1 << n;
//        final int result = value & ~bit;
//        target.write(result);
//
//        return 4 + target.cyclesCost() + target.cyclesCost();
//        
//        
    pc.increment(valueDelta);

    pc.increment(1);

    final int value = target.read();
    final int bit = 1 << n;
    final int result = value & ~bit;
    target.write(result);

    if (valueDelta != 0)
      pc.increment(1);

    return 4 + target.cyclesCost() + target.cyclesCost();
  }

  @Override
  public String toString() {
    return "RES " + n + ", " + target;
  }

}
