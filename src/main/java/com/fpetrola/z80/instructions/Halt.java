package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Halt<T extends WordNumber> extends AbstractInstruction<T> {
  private final State<T> state;

  public Halt(State state) {
    this.state = state;
  }

  @Override
  public int execute() {
    if (!state.isHalted())
      state.setHalted(true);

    return 4;
  }

  public void accept(InstructionVisitor visitor) {
    super.accept(visitor);
    visitor.visitingHalt(this);
  }

}
