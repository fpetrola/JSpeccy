package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Halt<T extends WordNumber> extends AbstractInstruction<T> {

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
