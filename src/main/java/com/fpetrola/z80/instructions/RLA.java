package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class RLA extends AbstractOpCode {

  private final OpcodeReference target;

  public RLA(State state, OpcodeReference target) {
    super(state);
    this.target = target;
  }

  public int execute() {

    pc.increment(1);

    final int value = target.read();
    target.write(flag.RLA(value));

    return 7 + target.cyclesCost();
  }

  public String toString() {
    return "RLA " + target;
  }

}
