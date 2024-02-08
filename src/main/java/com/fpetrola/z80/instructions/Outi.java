package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Outi<T extends WordNumber> extends AbstractInstruction<T> {
  public Outi(State state) {
    super(state);
  }

  public int execute() {

    spy.pause();

    T hlValue = hl.read();
    T valueFromHL = memory.read(hlValue);

    T cValue = bc.getLow().read();
    spy.doContinue();

    state.getIo().out(cValue, valueFromHL);

    spy.pause();

    hl.increment(1);
    b.decrement(1);

    flag.OUTI(b.read());

    spy.doContinue();

    return 1;
  }
}
