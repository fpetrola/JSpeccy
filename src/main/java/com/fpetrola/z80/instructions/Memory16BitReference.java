package com.fpetrola.z80.instructions;

import com.fpetrola.z80.OOZ80;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.Register;

public class Memory16BitReference implements OpcodeReference {

  private final Memory memory;
  private OpCode opCode;
  private int fetchedAddress;

  public Memory16BitReference(Memory memory) {
    this.memory = memory;
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
    Register pc = opCode.getPC();
    int address1 = pc.read();
    int lsb = memory.read(address1) & 0xff;
    fetchedAddress = ((memory.read(address1 + 1) << 8) & 0xff00 | lsb);

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

  public void setOpCode(OpCode opCode) {
    this.opCode = opCode;
  }

  public Object clone() throws CloneNotSupportedException {
    int lastFetchedAddress = fetchedAddress;
    return new Memory16BitReference(memory) {
      public int read() {
        return lastFetchedAddress;
      }
    };
  }
}
