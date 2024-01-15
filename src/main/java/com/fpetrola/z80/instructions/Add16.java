package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Add16 extends TargetSourceOpcode {

  public Add16(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target, source);
  }

  public int execute() {
    final int value1 = source.read();
    final int value2 = target.read();
    target.write(flag.ALU16BitAdd(value2, value1));

    return cyclesCost;
  }

  public String toString() {
    return "ADD " + target + ", " + source;
  }
}
