package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.registers.Flags;

public class BIT extends AbstractOpCode {

  private final OpcodeReference target;
  private final int n;
  private int valueDelta;

  public BIT(State state, OpcodeReference target, int n, int valueDelta) {
    super(state);
    this.target = target;
    this.n = n;
    this.valueDelta = valueDelta;
  }

  @Override
  public int execute() {

    pc.increment(valueDelta);

    pc.increment(1);

    final int value = target.read();
//    final int bit = 1 << n;
//    final int result = value | bit;
//    target.write(result);

    flag.testBit(value, n);
    
    if (valueDelta != 0)
      pc.increment(1);

//        incrementPC();

//        final int value = target.read();
//        final int bit = 1 << n;
//        final int result = value & bit;

//        if (result == 0) {
//            Flags.setFlag(flag, Flags.SIGNIFICANT_FLAG, false);
//            Flags.setFlag(flag, Flags.Y_FLAG, false);
//            Flags.setFlag(flag, Flags.X_FLAG, false);
//            Flags.setFlag(flag, Flags.ZERO_FLAG, true);
//            Flags.setFlag(flag, Flags.PARITY_FLAG, true);
//        } else {
//            Flags.setFlag(flag, Flags.SIGNIFICANT_FLAG, n == 7);
//            Flags.setFlag(flag, Flags.Y_FLAG, n == 5);
//            Flags.setFlag(flag, Flags.X_FLAG, n == 3);
//            Flags.setFlag(flag, Flags.ZERO_FLAG, false);
//            Flags.setFlag(flag, Flags.PARITY_FLAG, false);
//        }
//
//        Flags.setFlag(flag, Flags.HALF_CARRY_FLAG, true);
//        Flags.setFlag(flag, Flags.NEGATIVE_FLAG, false);

    return 4 + target.cyclesCost() + target.cyclesCost();
  }

  @Override
  public String toString() {
    return "BIT " + n + ", " + target;
  }

}
