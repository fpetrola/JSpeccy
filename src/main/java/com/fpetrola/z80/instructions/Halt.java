package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Halt extends AbstractOpCode {

    public Halt(State state) {
        super(state);
    }

    @Override
    public int execute() {

        if (!state.isHalted()) {
            state.setHalted(true);
        }

        return 4;
    }
}
