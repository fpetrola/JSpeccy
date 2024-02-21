package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Ini<T extends WordNumber> extends AbstractInstruction<T> {

  public Ini(State state) {
    super(state);
  }

  public int execute() {
    T cValue = c.read();
    T in = state.getIo().in(cValue);

    T hlValue = hl.read();

    memory.write(hlValue, in);

    b.decrement();
    hl.increment();

    flag.INI(b.read());
    return 1;
  }
}
