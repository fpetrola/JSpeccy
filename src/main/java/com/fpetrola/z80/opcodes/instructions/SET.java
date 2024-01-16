package com.fpetrola.z80.opcodes.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.opcodes.references.BitOperation;
import com.fpetrola.z80.opcodes.references.OpcodeReference;

public class SET extends BitOperation {

  public SET(State state, OpcodeReference target, int n, int valueDelta) {
    super(state, target, n, valueDelta);
  }

  public int execute() {
    target.write(target.read() | 1 << n);
    return cyclesCost;
  }
}
