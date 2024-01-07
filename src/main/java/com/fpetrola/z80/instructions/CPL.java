package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class CPL extends TargetOpCode {

  public CPL(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {

    final int a = target.read();

//        final boolean isSum = !Flags.getFlag(flag, Flags.NEGATIVE_FLAG);
//        final boolean isCarryFlagSet = Flags.getFlag(flag, Flags.CARRY_FLAG);
//        final boolean isHalfCarryFlagSet = Flags.getFlag(flag, Flags.HALF_CARRY_FLAG);
//
//        int result = a;
//
//        if (isSum) {
//
//            if (isHalfCarryFlagSet || (result & 0x0F) > 9) {
//                Flags.setFlag(flag, Flags.HALF_CARRY_FLAG, ((result & 0x0F) > 9));
//                result += 0x06;
//            }
//
//            if (isCarryFlagSet || ((result & 0x1f0) > 0x90)) {
//                result += 0x60;
//            }
//
//        } else {
//
//            boolean isCarryScenario = (result > 0x99);
//
//            if (isHalfCarryFlagSet || result > 0x09) {
//
//                if (result > 0x05) {
//                    Flags.setFlag(flag, Flags.HALF_CARRY_FLAG, false);
//                }
//
//                result -= 0x06;
//
//            }
//
//            if (isCarryFlagSet || isCarryScenario) {
//                result = (result & 0Xff) - 0x0160;
//            }
//
//        }
//
//        result &= 0xFF;
//
//        Flags.setFlag(flag, Flags.CARRY_FLAG, isCarryFlagSet || (result >= 0xFFFF));
//        Flags.copyFrom(flag, Flags.SIGNIFICANT_FLAG | Flags.Y_FLAG | Flags.X_FLAG, result);
//        Flags.setFlag(flag, Flags.ZERO_FLAG, (result == 0));
//        Flags.setFlag(flag, Flags.PARITY_FLAG, Z80Utils.isEvenParity8bit(result));

    int result = flag.CPL(a);

    target.write(result);

    return 4 + target.cyclesCost() + target.cyclesCost();
  }

  public String toString() {
    return "CPL " + target;
  }
}
