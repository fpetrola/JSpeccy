package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class RRCA extends TargetOpCode {

  public RRCA(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {

    final int value = target.read();
//        final int bit = (value & 0x01) << 7;
//        final int result = Z80Utils.mask8bit(value >>> 1 | bit);

    int rrca = flag.RRCA(value);
    target.write(rrca);
//
//        Flags.setFlag(flag, Flags.HALF_CARRY_FLAG, false);
//        Flags.setFlag(flag, Flags.NEGATIVE_FLAG, false);
//        Flags.copyFrom(flag, Flags.CARRY_FLAG | Flags.Y_FLAG | Flags.X_FLAG, result);

    return cyclesCost;
  }
}
