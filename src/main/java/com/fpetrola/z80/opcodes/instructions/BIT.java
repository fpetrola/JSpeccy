package com.fpetrola.z80.opcodes.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.opcodes.references.BitOperation;
import com.fpetrola.z80.opcodes.references.OpcodeReference;

public class BIT extends BitOperation {
  public BIT(State state, OpcodeReference target, int n, int valueDelta) {
    super(state, target, n, valueDelta);
  }

  public int execute() {
    final int value = target.read();
    flag.testBit(value, n);

    return cyclesCost;
  }
}
