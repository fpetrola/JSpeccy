package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.Condition;
import com.fpetrola.z80.registers.Register;

public class RetN extends Ret {
  private final State state;

  RetN(Condition condition, Register sp, Memory memory, State state) {
    super(condition, sp, memory);
    this.state = state;
  }

  public int execute() {
    state.setIff1(state.isIff2());
    return super.execute();
  }
}
