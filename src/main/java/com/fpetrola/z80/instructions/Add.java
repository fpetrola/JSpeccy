package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Add extends TargetSourceOpcode {

  public Add(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target, source);
  }

  public int execute() {
    final int value1 = source.read();
    final int value2 = target.read();
    int ALU8BitAdd = flag.ALU8BitAdd(value1, value2);
    target.write(ALU8BitAdd);

    return 4 + source.cyclesCost() + target.cyclesCost();
  }

  public String toString() {
    return "ADD " + target + ", " + source;
  }

}
