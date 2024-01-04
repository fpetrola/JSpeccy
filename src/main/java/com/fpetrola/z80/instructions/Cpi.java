package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Cpi extends AbstractOpCode {
  public Cpi(State state) {
    super(state);
  }

  public int execute() {
//    Cp cp = new Cp(state, target, source);
//    int alu8BitCp = ALU8BitCp(source.read(), target.read());
    hl.increment(1);
    bc.decrement(1);

    flag.CPI(hl.read(), a.read(), bc.read());

    pc.increment(1);

    return 1;
  }
}
