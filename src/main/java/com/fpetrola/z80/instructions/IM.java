package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.mmu.State.InterruptionMode;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class IM<T extends WordNumber> extends AbstractInstruction<T> {
  int mode;

  public IM(State state, int mode) {
    super(state);
    this.mode = mode;
  }

  public int execute() {
    state.setIntMode(InterruptionMode.values()[mode]);
    return 4;
  }

  public String toString() {
    return "IM" + mode;
  }

  public int getMode() {
    return mode;
  }
}
