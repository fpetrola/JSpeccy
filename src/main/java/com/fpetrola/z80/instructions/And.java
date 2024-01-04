package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class And extends AbstractOpCode {

  private final OpcodeReference target;
  private final OpcodeReference source;

  public And(State state, OpcodeReference target, OpcodeReference source) {
    super(state);
    this.target = target;
    this.source = source;
  }

  public int execute() {
    pc.increment(1);

    final int value1 = target.read();
    final int value2 = source.read();

    target.write(flag.ALU8BitAnd(value2, value1));

    return 4 + source.cyclesCost() + target.cyclesCost();
  }

  @Override
  public String toString() {
    return "AND " + source;
  }

}
