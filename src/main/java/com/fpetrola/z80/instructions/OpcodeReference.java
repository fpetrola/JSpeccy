package com.fpetrola.z80.instructions;

public interface OpcodeReference {

    int read();

    void write(int value);

    int cyclesCost();

}
