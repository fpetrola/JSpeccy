package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.mmu.State.InterruptionMode;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class IM<T extends WordNumber> extends AbstractInstruction<T> {
  int mode;
  private State<T> state;

  public IM(State state, int mode) {
    this.state = state;
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

  public void accept(InstructionVisitor visitor) {
    visitor.visitingIm(this);
  }
}
