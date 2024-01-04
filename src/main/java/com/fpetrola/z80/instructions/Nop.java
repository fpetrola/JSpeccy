package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Nop extends AbstractOpCode {

    public Nop(State state) {
        super(state);
    }

    @Override
    public int execute() {
        pc.increment(1);

        return 4;
    }

    @Override
    public String toString() {
        return "NOP";
    }
}
