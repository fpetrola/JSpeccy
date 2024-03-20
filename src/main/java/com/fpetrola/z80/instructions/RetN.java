package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.Condition;
import com.fpetrola.z80.registers.Register;

public class RetN extends Ret {
  private final State state;

  public RetN(Condition condition, Register sp, Memory memory, State state, Register pc) {
    super(condition, sp, memory, pc);
    this.state = state;
  }

  public int execute() {
    state.setIff1(state.isIff2());
    return super.execute();
  }
}
