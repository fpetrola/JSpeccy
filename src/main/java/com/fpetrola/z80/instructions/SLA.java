package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.Z80Utils;
import com.fpetrola.z80.registers.Flags;

public class SLA extends TargetOpCode {

  public SLA(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {

    final int value = target.read();
//        final int result = Z80Utils.mask8bit(value << 1);

    int shiftGenericSLA = flag.shiftGenericSLA(value);

    target.write(shiftGenericSLA);

//        Flags.setFlag(flag, Flags.HALF_CARRY_FLAG, false);
//        Flags.setFlag(flag, Flags.NEGATIVE_FLAG, false);
//        Flags.setFlag(flag, Flags.PARITY_FLAG, Z80Utils.isEvenParity8bit(result));
//        Flags.setFlag(flag, Flags.CARRY_FLAG, ((value & 0x80) == 0x80));
//        Flags.copyFrom(flag, Flags.SIGNIFICANT_FLAG | Flags.Y_FLAG | Flags.X_FLAG, result);

    return getCyclesCost();
  }

  @Override
  public String toString() {
    return "SLA " + target;
  }
}
