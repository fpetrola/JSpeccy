package com.fpetrola.z80.registers;

public interface RegisterPair<T> extends Register<T> {

    Register<T> getHigh();

    Register<T> getLow();
}
