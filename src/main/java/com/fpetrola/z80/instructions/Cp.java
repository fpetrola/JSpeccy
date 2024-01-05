package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Cp extends AbstractOpCode {

  private final OpcodeReference target;
  private final OpcodeReference source;

  public Cp(State state, OpcodeReference target, OpcodeReference source) {
    super(state);
    this.target = target;
    this.source = source;
  }

  @Override
  public int execute() {

    pc.increment(1);

    final int value1 = target.read();
    final int value2 = source.read();

    flag.ALU8BitCp(value2, value1);

    return 4 + source.cyclesCost() + target.cyclesCost();
  }

  @Override
  public String toString() {
    return "CP " + source;
  }
}
