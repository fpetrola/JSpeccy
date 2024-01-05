package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Dec16 extends AbstractOpCode {

  private final OpcodeReference target;

  public Dec16(State state, OpcodeReference target) {
    super(state);
    this.target = target;
  }

  @Override
  public int execute() {

    pc.increment(1);

    target.write(target.read() - 1 & 0xFFFF);

    return 6 + target.cyclesCost() + target.cyclesCost();
  }

  public String toString() {
    return "DEC16 " + target;
  }

}
