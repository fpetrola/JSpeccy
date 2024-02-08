package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Cpd<T extends WordNumber> extends AbstractInstruction<T> {

  public Cpd(State state) {
    super(state);
  }

  public int execute() {
    T hlValue = hl.read();
    T valueFromHL = memory.read(hlValue);
    hl.decrement(1);
    bc.decrement(1);

    flag.CPD(valueFromHL, a.read(), bc.read());

    return 1;
  }
}
