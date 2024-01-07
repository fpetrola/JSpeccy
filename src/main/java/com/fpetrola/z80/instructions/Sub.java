package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Sub extends TargetSourceOpcode {

  public Sub(State state, OpcodeReference target, OpcodeReference source) {
    super(state, source, source);
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
