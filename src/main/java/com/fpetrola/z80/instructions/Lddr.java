package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;

public class Lddr extends AbstractInstruction {

  public Lddr(State state) {
    super(state);
  }

  public int execute() {
    Ldd ldd = new Ldd(state);
    int execute = ldd.execute();

    boolean loop = bc.read() != 0;
    setNextPC(loop ? pc.read() : -1);

    return execute;
  }
}
