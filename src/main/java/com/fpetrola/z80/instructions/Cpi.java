package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Cpi<T extends WordNumber> extends AbstractInstruction<T> {

  public Cpi(State state) {
    super(state);
  }

  public int execute() {
    T hlValue = hl.read();
    T valueFromHL = memory.read(hlValue);
    hl.increment();
    bc.decrement();

    flag.CPI(valueFromHL, a.read(), bc.read());

    return 1;
  }
}
