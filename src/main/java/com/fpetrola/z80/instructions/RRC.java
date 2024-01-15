package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.Z80Utils;
import com.fpetrola.z80.registers.Flags;

public class RRC extends TargetOpCode {

  public RRC(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {

    final int value = target.read();
//        final int bit = (value & 0x01) << 7;
//        final int result = Z80Utils.mask8bit(value >>> 1 | bit);

    int shiftGenericRRC = flag.shiftGenericRRC(value);
    target.write(shiftGenericRRC);

//        Flags.setFlag(flag, Flags.HALF_CARRY_FLAG, false);
//        Flags.setFlag(flag, Flags.NEGATIVE_FLAG, false);
//        Flags.setFlag(flag, Flags.PARITY_FLAG, Z80Utils.isEvenParity8bit(result));
//        Flags.setFlag(flag, Flags.CARRY_FLAG, (bit != 0));
//        Flags.copyFrom(flag, Flags.SIGNIFICANT_FLAG | Flags.Y_FLAG | Flags.X_FLAG, result);

    return cyclesCost;
  }
}
