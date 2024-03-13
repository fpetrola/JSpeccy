package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class EI<T extends WordNumber> extends AbstractInstruction<T> {
  private State<T> state;

  public EI(State state) {
    this.state = state;
  }

  public int execute() {
    state.enableInterrupt();
    return 4;
  }
}
