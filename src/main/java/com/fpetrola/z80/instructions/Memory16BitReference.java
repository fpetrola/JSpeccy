package com.fpetrola.z80.instructions;

import com.fpetrola.z80.OOZ80;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.Register;

public class Memory16BitReference implements OpcodeReference {

  private final Memory memory;
  private int fetchedAddress;
  private Register pc;
  private int delta;

  public Memory16BitReference(Memory memory, Register pc, int delta) {
    this.memory = memory;
    this.pc = pc;
    this.delta = delta;
  }

  public int read() {
    return fetchAddress();
  }

  public void write(int value) {
    int address = fetchAddress();

    memory.write(address, value & 0xFF);
    memory.write(address + 1, (value >> 8));
  }

  private int fetchAddress() {
    int pcValue = pc.read() + delta;
    fetchedAddress = ((memory.read(pcValue + 1) << 8) & 0xff00 | memory.read(pcValue) & 0xff);

    return fetchedAddress;
  }

  public int cyclesCost() {
    return 3 + 3;
  }

  public String toString() {
    return OOZ80.convertToHex(read()) + "";
  }

  public int getLength() {
    return 2;
  }

  public Object clone() throws CloneNotSupportedException {
    int lastFetchedAddress = fetchedAddress;
    return new Memory16BitReference(memory, pc, delta) {
      public int read() {
        return lastFetchedAddress;
      }
    };
  }
}
