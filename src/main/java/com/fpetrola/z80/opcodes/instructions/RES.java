package com.fpetrola.z80.opcodes.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.opcodes.references.BitOperation;
import com.fpetrola.z80.opcodes.references.OpcodeReference;

public class RES extends BitOperation {

  public RES(State state, OpcodeReference target, int n, int valueDelta) {
    super(state, target, n, valueDelta);
  }

  public int execute() {
    final int value = target.read();
    final int bit = 1 << n;
    final int result = value & ~bit;
    target.write(result);

    return cyclesCost;
  }
}
