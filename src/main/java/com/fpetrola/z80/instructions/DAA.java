package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class DAA extends TargetOpCode {

  public DAA(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    final int a = target.read();
//        final int result = ~a;

//        Flags.copyFrom(flag, Flags.Y_FLAG | Flags.X_FLAG, result);
//        Flags.setFlag(flag, Flags.HALF_CARRY_FLAG, true);
//        Flags.setFlag(flag, Flags.NEGATIVE_FLAG, true);

    target.write(flag.DAA(a));

    return cyclesCost;
  }

}
