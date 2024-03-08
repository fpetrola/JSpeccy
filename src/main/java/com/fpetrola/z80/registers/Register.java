package com.fpetrola.z80.registers;

import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;

public interface Register<T> extends OpcodeReference<T> {
    void increment();
    void decrement();
    String getName();
}
