package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Ldi<T extends WordNumber> extends AbstractInstruction<T> {

  public Ldi(State state) {
    super(state);
  }

  public int execute() {
    spy.pause();
    T hlValue = hl.read();
    T deValue = de.read();
    T aValue = a.read();
    spy.doContinue();
    T work8 = memory.read(hlValue);
    memory.write(deValue, work8);
    spy.pause();

    hl.increment();
    de.increment();
    bc.decrement();

    flag.LDI(bc.read());
    spy.doContinue();

    return 1;
  }
}
