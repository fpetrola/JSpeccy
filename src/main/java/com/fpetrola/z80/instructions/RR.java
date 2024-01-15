package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class RR extends TargetOpCode {

  public RR(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {

    final int value = target.read();
    int shiftGenericRR = flag.shiftGenericRR(value);
    target.write(shiftGenericRR);

    return cyclesCost;
  }
}
