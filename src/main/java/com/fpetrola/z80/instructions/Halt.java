package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Halt extends AbstractOpCode {

    public Halt(State state) {
        super(state);
    }

    @Override
    public int execute() {

        if (!state.isHalted()) {
            pc.increment(1);

            state.setHalt(true);
        }

        return 4;
    }

    @Override
    public String toString() {
        return "HALT";
    }
}
