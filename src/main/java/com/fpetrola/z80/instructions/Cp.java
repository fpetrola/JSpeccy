package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.registers.Flags;

public class Cp extends AbstractOpCode {

    private final OpcodeReference target;
    private final OpcodeReference source;

    public Cp(State state, OpcodeReference target, OpcodeReference source) {
        super(state);
        this.target = target;
        this.source = source;
    }

    @Override
    public int execute() {

        pc.increment(1);

        final int value1 = target.read();
        final int value2 = source.read();
//        final int result = value1 - value2;
//        final int carry = value1 ^ value2 ^ result;

//        Flags.setFlag(flag, Flags.CARRY_FLAG, ((carry & 0x100) == 0x100));
//        Flags.setFlag(flag, Flags.HALF_CARRY_FLAG, ((carry & 0x10) == 0x10));
//        Flags.setFlag(flag, Flags.PARITY_FLAG, ((((carry >>> 1) ^ carry) & 0x80) == 0x80));
//        Flags.setFlag(flag, Flags.ZERO_FLAG, ((result & 0xff) == 0));
//        Flags.copyFrom(flag, Flags.SIGNIFICANT_FLAG | Flags.Y_FLAG | Flags.X_FLAG, result);
//        Flags.setFlag(flag, Flags.NEGATIVE_FLAG, true);

        
        flag.ALU8BitCp(value2, value1);
        
        return 4 + source.cyclesCost() + target.cyclesCost();
    }

    @Override
    public String toString() {
        return "CP " + source;
    }
}
