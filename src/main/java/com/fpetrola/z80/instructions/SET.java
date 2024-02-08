package com.fpetrola.z80.instructions;

import com.fpetrola.z80.cpu.OOZ80;
import com.fpetrola.z80.instructions.base.BitOperation;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class SET<T extends WordNumber> extends BitOperation<T> {

  public SET(State state, OpcodeReference target, int n, int valueDelta) {
    super(state, target, n, valueDelta);
  }

  public int execute() {
    target.write(target.read().or(1 << n));
    return cyclesCost;
  }
}
