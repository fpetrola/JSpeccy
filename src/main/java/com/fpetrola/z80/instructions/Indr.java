package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Indr<T extends WordNumber> extends RepeatingInstruction<T> {
  public Indr(State state) {
    super(state, new Ind(state));
  }

  protected boolean checkLoopCondition() {
    return b.read().notEquals(0);
  }
}
