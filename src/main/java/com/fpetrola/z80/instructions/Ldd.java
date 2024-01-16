package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;

public class Ldd extends AbstractInstruction {

  public Ldd(State state) {
    super(state);
  }

  public int execute() {
    int hlValue = hl.read();
    int deValue = de.read();
    int work8 = memory.read(hlValue);
    memory.write(deValue, work8);
    
    hl.decrement(1);
    de.decrement(1);
    bc.decrement(1);
    flag.LDD(a.read(), hl.read(), bc.read());
    return 1;
  }
}
