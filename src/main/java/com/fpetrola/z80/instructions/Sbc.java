package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Sbc extends TargetSourceOpcode {

  public Sbc(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target, source);
  }

  public int execute() {

    final int value1 = target.read();
    final int value2 = source.read();
    int result = flag.ALU8BitSbc(value2, value1);
    target.write(result);

    return 4 + source.cyclesCost() + target.cyclesCost();
  }

  public String toString() {
    return "SBC " + target + ", " + source;
  }
}
