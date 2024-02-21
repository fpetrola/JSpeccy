package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public class Ind<T extends WordNumber> extends AbstractInstruction<T> {

  public Ind(State<T> state) {
    super(state);
  }

  public int execute() {
    T cValue = c.read();
    T in = state.getIo().in(cValue);

    T hlValue = hl.read();

    memory.write(hlValue, in);

    b.decrement();
    hl.decrement();

    flag.IND(b.read());
    return 1;
  }
}
