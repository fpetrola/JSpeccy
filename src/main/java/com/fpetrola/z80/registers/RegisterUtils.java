package com.fpetrola.z80.registers;

import com.fpetrola.z80.mmu.Memory;

public final class RegisterUtils {

    public static final int indirect(Memory memory, Register r) {
        return memory.read(r.read());
    }

}
