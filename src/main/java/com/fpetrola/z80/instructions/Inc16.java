package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Inc16 extends TargetOpCode {

  public Inc16(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {

    pc.increment(1);

    target.write(target.read() + 1 & 0xFFFF);

    return 6 + target.cyclesCost() + target.cyclesCost();
  }

  public String toString() {
    return "INC " + target;
  }

}
