package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.Z80Utils;
import com.fpetrola.z80.registers.Flags;

public class RRA extends TargetOpCode {

  public RRA(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {

    final int value = target.read();
//        final int bit = Flags.getFlag(flag, Flags.CARRY_FLAG) ? 0x80 : 0x00;
//        final int result = Z80Utils.mask8bit(value >>> 1 | bit);
    target.write(flag.RRA(value));

//        Flags.setFlag(flag, Flags.CARRY_FLAG, ((value & 0x01) == 0x01));
//        Flags.setFlag(flag, Flags.HALF_CARRY_FLAG, false);
//        Flags.setFlag(flag, Flags.NEGATIVE_FLAG, false);
//        Flags.copyFrom(flag, Flags.Y_FLAG | Flags.X_FLAG, result);

    return getCyclesCost();
  }

  @Override
  public String toString() {
    return "RR " + target;
  }

}
