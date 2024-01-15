package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Inc16 extends TargetOpCode {

  public Inc16(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    target.write(target.read() + 1 & 0xFFFF);

    return cyclesCost;
  }

  public String toString() {
    return "INC " + target;
  }

}
