package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.Register;

/**
 * Read 16-bit from PC+1
 *
 * @author fpreto
 */
public final class Memory16BitReference implements OpcodeReference {

  public final Register pc;
  private final Memory memory;

  public Memory16BitReference(Register pc, Memory memory) {
    this.pc = pc;
    this.memory = memory;
  }

  public int read() {
    int address = pc.read();
    int lsb = memory.read(address) & 0xff;
    int value = ((memory.read(address + 1) << 8) & 0xff00 | lsb);
    pc.increment(2);
    return value;
  }

  public void write(int value) {
    int address1 = pc.read();
    int lsb = memory.read(address1) & 0xff;
    int address = ((memory.read(address1 + 1) << 8) & 0xff00 | lsb);
    memory.write(address, value & 0xFF);
    memory.write(address + 1, (value >> 8));
    pc.increment(2);
  }

  public int cyclesCost() {
    return 3 + 3;
  }

  public String toString() {
    return "nn";
  }
}
