package com.fpetrola.z80.opcodes.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.opcodes.references.AbstractInstruction;

public class Halt extends AbstractInstruction {

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
