package com.fpetrola.z80.registers;

public interface RegisterPair extends Register {

    Register getHigh();

    Register getLow();

}
