package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Inc16 extends AbstractOpCode {

    private final OpcodeReference target;

    public Inc16(State state, OpcodeReference target) {
        super(state);
        this.target = target;
    }

    @Override
    public int execute() {

        pc.increment(1);

        target.write(target.read() + 1 & 0xFFFF);

        return 6 + target.cyclesCost() + target.cyclesCost();
    }

    @Override
    public String toString() {
        return "INC " + target;
    }

}
