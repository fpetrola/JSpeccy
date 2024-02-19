package com.fpetrola.z80.registers;

import com.fpetrola.z80.opcodes.references.OpcodeReference;

public interface Register<T> extends OpcodeReference<T> {
    T read();
    void write(T value);
    void increment();
    void decrement();
    RegisterName getName();
}
