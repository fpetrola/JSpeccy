package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Add16 extends AbstractOpCode {

  private final OpcodeReference target;
  private final OpcodeReference source;

  public Add16(State state, OpcodeReference target, OpcodeReference source) {
    super(state);
    this.target = target;
    this.source = source;
  }

  @Override
  public int execute() {

    pc.increment(1);

    final int value1 = source.read();
    final int value2 = target.read();
    target.write(flag.ALU16BitAdd(value2, value1));

    return 11 + source.cyclesCost() + target.cyclesCost();
  }

  @Override
  public String toString() {
    return "Add16:" + source + " - " + target;
  }

}
