package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;

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
