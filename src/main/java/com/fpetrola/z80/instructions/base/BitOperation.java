package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;

public abstract class BitOperation extends TargetInstruction {

  protected final int n;
  protected int valueDelta;

  public BitOperation(State state, OpcodeReference target, int n, int valueDelta) {
    super(state, target);
    this.n = n;
    this.valueDelta = valueDelta;
    incrementLengthBy(valueDelta != 0 ? 1 : 0);
  }

  public String toString() {
    return spy.executeInPause(() -> getClass().getSimpleName() + n + ", " + target);
  }

  public int getValueDelta() {
    return valueDelta;
  }

  public int getN() {
    return n;
  }
}