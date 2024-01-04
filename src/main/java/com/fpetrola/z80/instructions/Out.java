package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.mmu.IO;

public class Out extends AbstractOpCode {

    private final OpcodeReference target;
    private final OpcodeReference source;
    private final IO io;

    public Out(State state, OpcodeReference target, OpcodeReference source, IO io) {
        super(state);
        this.target = target;
        this.source = source;
        this.io = io;
    }

    @Override
    public int execute() {

        pc.increment(1);

        int port = target.read();
        int value = source.read();
        //io.out(port, value);


        return 4 + target.cyclesCost() + 4;
    }

    @Override
    public String toString() {
        return "OUT " + target + "," + source;
    }

}
