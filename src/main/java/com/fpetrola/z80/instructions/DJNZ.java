package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;

public class DJNZ extends TargetInstruction {

  public DJNZ(State state, OpcodeReference source) {
    super(state, source);
  }

  public int execute() {
    b.decrement(1);

    byte by = (byte) target.read();

    if (b.read() != 0)
      state.setNextPC(pc.read() + by + length);

    return cyclesCost;
  }
}
