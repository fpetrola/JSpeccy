package com.fpetrola.z80.mmu;

public interface IO {

    /**
     * Read 8-bit data from the given port
     *
     * @param port port to read the data
     * @return value available at the port
     */
    int in(int port);

    /**
     * Write 8-bit data into given port
     *
     * @param port target port
     * @param value to be written
     */
    void out(int port, int value);
}
