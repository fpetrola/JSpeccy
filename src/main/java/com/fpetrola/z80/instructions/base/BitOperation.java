package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public abstract class BitOperation<T extends WordNumber> extends InvertedFetchInstruction<T> {
  protected final int n;

  public BitOperation(State state, OpcodeReference<T> target, int n, int valueDelta) {
    super(state, target, valueDelta);
    this.n = n;
  }

  public String toString() {
    return getClass().getSimpleName() + " " + n + ", " + target;
  }

  public int getN() {
    return n;
  }
}