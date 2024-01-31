package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.State;

public class Lddr extends RepeatingInstruction {
  public Lddr(State state) {
    super(state, new Ldd(state));
  }
}
