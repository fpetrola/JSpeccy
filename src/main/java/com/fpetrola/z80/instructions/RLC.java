package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class RLC extends TargetOpCode {

  public RLC(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    final int value = target.read();
    int shiftGenericRLC = flag.shiftGenericRLC(value);
    target.write(shiftGenericRLC);

    return cyclesCost;
  }
}
