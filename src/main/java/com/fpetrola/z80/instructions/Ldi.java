package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.mmu.Memory;

public class Ldi extends AbstractOpCode {

  protected Memory memory;

  public Ldi(State state, OpcodeTargets opt) {
    super(state);
    memory = opt.memory();
  }

  public int execute() {
    int hlValue = hl.read();
    int deValue = de.read();
    int aValue = a.read();

    int work8 = memory.read(hlValue);
    memory.write(deValue, work8);

    hl.increment(1);
    de.increment(1);
    bc.decrement(1);

//    flag.LDI(aValue, hlValue, bcValue);
    flag.LDI(aValue, work8, bc.read());

    pc.increment(1);

    return 1;
  }
}
