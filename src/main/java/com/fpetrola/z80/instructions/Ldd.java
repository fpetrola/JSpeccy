package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Ldd<T extends WordNumber> extends AbstractInstruction<T> {

  public Ldd(State state) {
    super(state);
  }

  public int execute() {
    T hlValue = hl.read();
    T deValue = de.read();
    T work8 = memory.read(hlValue);
    memory.write(deValue, work8);
    
    hl.decrement();
    de.decrement();
    bc.decrement();
    flag.LDD(a.read(), hl.read(), bc.read());
    return 1;
  }
}
