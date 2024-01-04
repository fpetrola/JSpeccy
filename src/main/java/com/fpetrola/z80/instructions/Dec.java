package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.Z80Utils;
import com.fpetrola.z80.registers.Flags;

public class Dec extends AbstractOpCode {

    private final OpcodeReference target;

    public Dec(State state, OpcodeReference target) {
        super(state);
        this.target = target;
    }

    @Override
    public int execute() {

        pc.increment(1);

        final int value = target.read();
//        final int result = Z80Utils.mask8bit(value - 1);
        target.write(flag.ALU8BitDec(value));

//        Flags.setFlag(flag, Flags.ZERO_FLAG, (result == 0));
//        Flags.setFlag(flag, Flags.HALF_CARRY_FLAG, ((result & 0x0F) == 0x0F));
//        Flags.copyFrom(flag, Flags.SIGNIFICANT_FLAG | Flags.Y_FLAG | Flags.X_FLAG, result);
//        Flags.setFlag(flag, Flags.PARITY_FLAG, (result == 0x7F));
//        Flags.setFlag(flag, Flags.NEGATIVE_FLAG, true);

        return 4 + target.cyclesCost() + target.cyclesCost();
    }
    
    @Override
    public String toString() {
        return "DEC " + target;
    }


}
