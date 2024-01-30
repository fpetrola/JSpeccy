package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.helpers.StringHelper;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.spy.InstructionSpy;

public class Memory16BitReference implements OpcodeReference {

  private final Memory memory;
  private int fetchedAddress;
  private Register pc;
  private int delta;
  private InstructionSpy spy;

  public Memory16BitReference(Memory memory, Register pc, int delta, InstructionSpy spy) {
    this.memory = memory;
    this.pc = pc;
    this.delta = delta;
    this.spy = spy;
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
    spy.pause();
    int pcValue = pc.read() + delta;
    fetchedAddress = ((memory.read(pcValue + 1) << 8) & 0xff00 | memory.read(pcValue) & 0xff);
    spy.doContinue();

    return fetchedAddress;
  }

  public int cyclesCost() {
    return 3 + 3;
  }

  public String toString() {
    return StringHelper.convertToHex(read()) + "";
  }

  public int getLength() {
    return 2;
  }

  public Object clone() throws CloneNotSupportedException {
    int lastFetchedAddress = fetchedAddress;
    return new Memory16BitReference(memory, pc, delta, spy) {
      public int read() {
        return lastFetchedAddress;
      }
    };
  }
}
