package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.State;

public class Ldir extends RepeatingInstruction {
  public Ldir(State state) {
    super(state, new Ldi(state));
  }
}
