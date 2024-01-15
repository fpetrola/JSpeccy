package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class SLL extends TargetOpCode {

  public SLL(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    final int value = target.read();
    target.write(flag.shiftGenericSLL(value));

    return cyclesCost;
  }
}
