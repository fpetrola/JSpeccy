package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class SRA extends TargetOpCode {

  public SRA(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {

    final int value = target.read();
    int shiftGenericSRA = flag.shiftGenericSRA(value);
    target.write(shiftGenericSRA);

    return cyclesCost;
  }
}
