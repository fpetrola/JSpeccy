package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Outdr<T extends WordNumber> extends RepeatingInstruction<T> {
  public Outdr(State state) {
    super(state, new Outd(state));
  }
}
