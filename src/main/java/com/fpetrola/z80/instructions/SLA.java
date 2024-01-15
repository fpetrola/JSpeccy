package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class SLA extends TargetOpCode {

  public SLA(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {

    final int value = target.read();
    int shiftGenericSLA = flag.shiftGenericSLA(value);
    target.write(shiftGenericSLA);

    return cyclesCost;
  }
}
