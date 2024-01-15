package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class RRCA extends TargetOpCode {

  public RRCA(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {

    final int value = target.read();
    int rrca = flag.RRCA(value);
    target.write(rrca);

    return cyclesCost;
  }
}
