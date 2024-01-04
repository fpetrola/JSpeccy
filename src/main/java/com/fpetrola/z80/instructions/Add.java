package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Add extends AbstractOpCode {

  private final OpcodeReference target;
  private final OpcodeReference source;

  public Add(State state, OpcodeReference target, OpcodeReference source) {
    super(state);
    this.target = target;
    this.source = source;
  }

  @Override
  public int execute() {

    pc.increment(1);

    final int value1 = source.read();
    final int value2 = target.read();
//        final int result = value1 & value2;
//        final int carry = value1 ^ value2 ^ result;
//        target.write(result);

    int ALU8BitAdd = flag.ALU8BitAdd(value1, value2);
    target.write(ALU8BitAdd);

//        Flags.setFlag(flag, Flags.CARRY_FLAG, ((carry & 0x100) == 0x100));
//        Flags.setFlag(flag, Flags.HALF_CARRY_FLAG, ((carry & 0x10) == 0x10));
//        Flags.setFlag(flag, Flags.PARITY_FLAG, ((((carry >>> 1) ^ carry) & 0x80) == 0x80));
//        Flags.setFlag(flag, Flags.ZERO_FLAG, ((result & 0xff) == 0));
//        Flags.copyFrom(flag, Flags.SIGNIFICANT_FLAG | Flags.Y_FLAG | Flags.X_FLAG, result);
//        Flags.setFlag(flag, Flags.NEGATIVE_FLAG, false);

    return 4 + source.cyclesCost() + target.cyclesCost();
  }

}
