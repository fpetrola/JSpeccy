package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public abstract class InvertedFetchInstruction<T extends WordNumber> extends TargetInstruction<T> {
  protected int valueDelta;

  public InvertedFetchInstruction(State state, OpcodeReference<T> target, int valueDelta) {
    super(state, target);
    this.valueDelta = valueDelta;
    if (valueDelta != 0)
      incrementLengthBy(1);
    else
      incrementLengthBy(0);
  }

  public int getValueDelta() {
    return valueDelta;
  }
}
