package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public class Outd<T extends WordNumber> extends AbstractInstruction<T> {
  public Outd(State state) {
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

    hl.decrement(1);
    Register<T> b = bc.getHigh();
    b.decrement(1);

    flag.OUTD(b.read());

    spy.doContinue();

    return 1;
  }
}
