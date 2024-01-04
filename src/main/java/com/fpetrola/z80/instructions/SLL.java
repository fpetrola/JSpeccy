package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class SLL extends AbstractOpCode {

  private final OpcodeReference target;

  public SLL(State state, OpcodeReference target) {
    super(state);
    this.target = target;
  }

  @Override
  public int execute() {
    pc.increment(1);

    final int value = target.read();
    target.write(flag.shiftGenericSLL(value));

    return 4 + target.cyclesCost() + target.cyclesCost();
  }

  @Override
  public String toString() {
    return "SLL " + target;
  }

}
