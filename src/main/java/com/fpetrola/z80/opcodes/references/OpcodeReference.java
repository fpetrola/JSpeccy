package com.fpetrola.z80.opcodes.references;

public interface OpcodeReference<T> extends ImmutableOpcodeReference<T>, MutableOpcodeReference<T> {
    @Override
    default public Object clone() throws CloneNotSupportedException {
        return null;
    }
}
