package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.mmu.Memory;

public class Outi extends AbstractOpCode {
  private Memory memory;
  private IO io;

  public Outi(State state, OpcodeTargets opt, IO io) {
    super(state);
    this.io = io;
    memory = opt.memory();
  }

  public int execute() {

    int hlValue = hl.read();
    int valueFromHL = memory.read(hlValue);

    int cValue = bc.getLow().read();
    io.out(cValue, valueFromHL);

    hl.increment(1);
    bc.getHigh().decrement(1);

    pc.increment(1);

    return 1;
  }
}
