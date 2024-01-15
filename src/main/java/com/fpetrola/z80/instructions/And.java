package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class And extends TargetSourceOpcode {
  public And(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target, source);
  }

  public int execute() {
    final int value1 = target.read();
    final int value2 = source.read();

    int alu8BitAnd = flag.ALU8BitAnd(value2, value1);
    target.write(alu8BitAnd);
    return cyclesCost;
  }
}
