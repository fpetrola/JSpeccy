package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.Z80Utils;
import com.fpetrola.z80.registers.Flags;

public class Xor extends AbstractOpCode {

  private final OpcodeReference target;
  private final OpcodeReference source;

  public Xor(State state, OpcodeReference target, OpcodeReference source) {
    super(state);
    this.target = target;
    this.source = source;
  }

  @Override
  public int execute() {
    pc.increment(1);

    final int value1 = target.read();
    final int value2 = source.read();
    int aLU8BitXor = flag.ALU8BitXor(value2, value1);
    target.write(aLU8BitXor);
//      
//      
//        incrementPC();
//
//        final int value1 = target.read();
//        final int value2 = source.read();
//        final int result = value1 ^ value2;
//        target.write(result);
//
//        Flags.setFlag(flag, Flags.CARRY_FLAG, false);
//        Flags.setFlag(flag, Flags.HALF_CARRY_FLAG, false);
//        Flags.setFlag(flag, Flags.PARITY_FLAG, Z80Utils.isEvenParity8bit(result));
//        Flags.setFlag(flag, Flags.ZERO_FLAG, ((result & 0xff) == 0));
//        Flags.copyFrom(flag, Flags.SIGNIFICANT_FLAG | Flags.Y_FLAG | Flags.X_FLAG, result);
//        Flags.setFlag(flag, Flags.NEGATIVE_FLAG, false);

    return 4 + source.cyclesCost() + target.cyclesCost();
  }

}
