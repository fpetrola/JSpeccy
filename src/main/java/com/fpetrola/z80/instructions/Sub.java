package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Sub extends AbstractOpCode {

  private final OpcodeReference target;
  private final OpcodeReference source;

  public Sub(State state, OpcodeReference target, OpcodeReference source) {
    super(state);
    this.target = target;
    this.source = source;
  }

  public int execute() {

    pc.increment(1);

    final int value1 = target.read();
    final int value2 = source.read();

    int alu8BitSub = flag.ALU8BitSub(value2, value1);
    target.write(alu8BitSub);

    return 4 + source.cyclesCost() + target.cyclesCost();
  }

  public String toString() {
    return "SUB " + target + ", " + source;
  }

}
