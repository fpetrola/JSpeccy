package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Outir<T extends WordNumber> extends RepeatingInstruction<T> {
  public Outir(State state) {
    super(state, new Outi(state));
  }

  protected boolean checkLoopCondition() {
    return b.read().notEquals(0);
  }
}
