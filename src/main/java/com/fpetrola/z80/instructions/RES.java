package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.BitOperation;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class RES<T extends WordNumber> extends BitOperation<T> {

  RES(OpcodeReference target, int n, int valueDelta) {
    super(target, n, valueDelta);
  }

  public int execute() {
    target.write(target.read().and(~(1 << n)));
    return cyclesCost;
  }
}
