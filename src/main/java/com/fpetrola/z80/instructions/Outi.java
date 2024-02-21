package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Outi<T extends WordNumber> extends AbstractInstruction<T> {
  public Outi(State state) {
    super(state);
  }

  public int execute() {
    T hlValue = hl.read();
    T valueFromHL = memory.read(hlValue);

    T cValue = c.read();

    state.getIo().out(cValue, valueFromHL);

    hl.increment();
    b.decrement();

    flag.OUTI(b.read());

    return 1;
  }
}
