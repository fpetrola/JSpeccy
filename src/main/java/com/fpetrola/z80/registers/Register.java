package com.fpetrola.z80.registers;

import com.fpetrola.z80.opcodes.references.OpcodeReference;

/**
 * 8-bit or 16-bit register that can be redden and written.
 *
 * @author fpreto
 */
public interface Register<T> extends OpcodeReference<T> {

    /**
     * Read the data from register
     */
    T read();

    /**
     * Write data to register
     */
    void write(T value);

    
    public void increment();
    public void decrement();
    public RegisterName getName();
}
