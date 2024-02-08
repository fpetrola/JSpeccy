package com.fpetrola.z80.registers;

import com.fpetrola.z80.mmu.Memory;

public final class RegisterUtils {

    public static final <T> T indirect(Memory<T> memory, Register<T> r) {
        return memory.read(r.read());
    }

}
