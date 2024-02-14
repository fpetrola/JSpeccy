package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Ldir<T extends WordNumber> extends RepeatingInstruction<T> {
  public Ldir(State state) {
    super(state, new Ldi(state));
  }

  protected boolean checkLoopCondition() {
    return bc.read().isNotZero();
  }
}
