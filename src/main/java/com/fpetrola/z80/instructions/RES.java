package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.BitOperation;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class RES<T extends WordNumber> extends BitOperation<T> {

  public RES(State state, OpcodeReference target, int n, int valueDelta) {
    super(state, target, n, valueDelta);
  }

  public int execute() {
    final T value = target.read();
    final int bit = 1 << n;
    final T result = value.and(~bit);
    target.write(result);

    return cyclesCost;
  }
}
