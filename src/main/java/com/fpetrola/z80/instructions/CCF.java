package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class CCF<T extends WordNumber> extends AbstractInstruction<T> {

  public CCF(State state) {
    super(state);
  }

  public int execute() {
    flag.CCF(a.read());
    return 4;
  }

}
