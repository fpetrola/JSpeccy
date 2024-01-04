package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class DAA extends AbstractOpCode {

  private final OpcodeReference target;

  public DAA(State state, OpcodeReference target) {
    super(state);
    this.target = target;
  }

  @Override
  public int execute() {

    pc.increment(1);

    final int a = target.read();
//        final int result = ~a;

//        Flags.copyFrom(flag, Flags.Y_FLAG | Flags.X_FLAG, result);
//        Flags.setFlag(flag, Flags.HALF_CARRY_FLAG, true);
//        Flags.setFlag(flag, Flags.NEGATIVE_FLAG, true);

    target.write(flag.DAA(a));

    return 4 + target.cyclesCost() + target.cyclesCost();
  }

}
