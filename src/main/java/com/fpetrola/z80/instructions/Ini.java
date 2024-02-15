package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Ini<T extends WordNumber> extends AbstractInstruction<T> {

  public Ini(State state) {
    super(state);
  }

  public int execute() {
    spy.pause();

    T cValue = bc.getLow().read();
    T in = state.getIo().in(cValue);

    T hlValue = hl.read();
    spy.doContinue();

    memory.write(hlValue, in);

    spy.pause();

    b.decrement();
    hl.increment();

    flag.INI(b.read());
    spy.doContinue();
    return 1;
  }
}
