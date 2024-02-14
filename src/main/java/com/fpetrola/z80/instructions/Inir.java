package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Inir<T extends WordNumber> extends RepeatingInstruction<T> {
  public Inir(State state) {
    super(state, new Ini(state));
  }
}
