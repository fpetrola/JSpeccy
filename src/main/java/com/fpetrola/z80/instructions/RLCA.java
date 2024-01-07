package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class RLCA  extends TargetOpCode {

  public RLCA(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    target.write(flag.RLCA(target));

    return 7 + target.cyclesCost() + target.cyclesCost();
  }

  @Override
  public String toString() {
    return "RLCA " + target;
  }

}
