package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.registers.Flags;

public class Neg extends AbstractOpCode {

  private final OpcodeReference target;

  public Neg(State state, OpcodeReference target) {
    super(state);
    this.target = target;
  }

  @Override
  public int execute() {
    pc.increment(1);

    int neg = flag.NEG(target.read());
    target.write(neg);

    return 4 + target.cyclesCost();
  }

}
