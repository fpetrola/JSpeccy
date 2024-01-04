package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class EI extends AbstractOpCode {

    public EI(State state) {
        super(state);
    }

    @Override
    public int execute() {
        pc.increment(1);

        state.enableInterrupt();

        return 4;
    }

    @Override
    public String toString() {
        return "EI";
    }
}
