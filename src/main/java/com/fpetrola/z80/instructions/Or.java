package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Or extends AbstractOpCode {

  private final OpcodeReference target;
  private final OpcodeReference source;

  public Or(State state, OpcodeReference target, OpcodeReference source) {
    super(state);
    this.target = target;
    this.source = source;
  }

  @Override
  public int execute() {

    pc.increment(1);

    final int value1 = target.read();
    final int value2 = source.read();

    int alu8BitOr = flag.ALU8BitOr(value2, value1);

    target.write(alu8BitOr);

    return 4 + source.cyclesCost() + target.cyclesCost();
  }

  @Override
  public String toString() {
    return "OR " + source + " - " + target;
  }

}
