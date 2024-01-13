package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.Memory;

/**
 * Read 8-bit pointed by the 16-bit register address
 *
 * @author fpreto
 */
public final class IndirectMemory8BitReference implements OpcodeReference {

    public final OpcodeReference target;
    private final Memory memory;

    public IndirectMemory8BitReference(OpcodeReference target, Memory memory) {
        this.target = target;
        this.memory = memory;
    }

    @Override
    public int read() {
        final int value = memory.read(target.read());
        return value;
    }

    @Override
    public void write(int value) {
        memory.write(target.read(), value);
    }

    @Override
    public int cyclesCost() {
        return 3 + target.cyclesCost();
    }

    @Override
    public String toString() {
        return "(" + target + ")8";
    }
    

    public int getLength() {
      return target.getLength();
    }

    public void setOpCode(OpCode opCode) {
      target.setOpCode(opCode);
    }
}
