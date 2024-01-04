package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.Z80Utils;
import com.fpetrola.z80.registers.Flags;

public class SRA extends AbstractOpCode {

  private final OpcodeReference target;

  public SRA(State state, OpcodeReference target) {
    super(state);
    this.target = target;
  }

  @Override
  public int execute() {

    pc.increment(1);

    final int value = target.read();
//        final int bit = value & 0x80;
//        final int result = Z80Utils.mask8bit(value >>> 1 | bit);

    int shiftGenericSRA = flag.shiftGenericSRA(value);
    target.write(shiftGenericSRA);

//        Flags.setFlag(flag, Flags.HALF_CARRY_FLAG, false);
//        Flags.setFlag(flag, Flags.NEGATIVE_FLAG, false);
//        Flags.setFlag(flag, Flags.PARITY_FLAG, Z80Utils.isEvenParity8bit(result));
//        Flags.setFlag(flag, Flags.CARRY_FLAG, ((value & 0x01) == 0x01));
//        Flags.copyFrom(flag, Flags.SIGNIFICANT_FLAG | Flags.Y_FLAG | Flags.X_FLAG, result);

    return 4 + target.cyclesCost() + target.cyclesCost();
  }

  @Override
  public String toString() {
    return "SRA " + target;
  }

}
