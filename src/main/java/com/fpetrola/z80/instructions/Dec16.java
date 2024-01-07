package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Dec16 extends TargetOpCode {

  public Dec16(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {

    pc.increment(1);

    target.write(target.read() - 1 & 0xFFFF);

    return 6 + target.cyclesCost() + target.cyclesCost();
  }

  public String toString() {
    return "DEC16 " + target;
  }

}
