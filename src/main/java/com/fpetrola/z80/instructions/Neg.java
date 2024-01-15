package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Neg extends TargetOpCode {
  public Neg(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    int neg = flag.NEG(target.read());
    target.write(neg);

    return cyclesCost;
  }

}
