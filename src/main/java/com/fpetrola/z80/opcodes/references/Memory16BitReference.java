package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.spy.InstructionSpy;

public class Memory16BitReference<T extends WordNumber> implements OpcodeReference<T> {

  private final Memory<T> memory;
  private T fetchedAddress;
  private Register<T> pc;
  private int delta;
  private InstructionSpy spy;

  public Memory16BitReference(Memory memory, Register pc, int delta, InstructionSpy spy) {
    this.memory = memory;
    this.pc = pc;
    this.delta = delta;
    this.spy = spy;
  }

  public T read() {
    return fetchAddress();
  }

  public void write(T value) {
    T address = fetchAddress();
    Helper.write16Bits(value,address, memory);
  }

  private T fetchAddress() {
    spy.pause();
    T pcValue = pc.read().plus(delta);
    fetchedAddress = Helper.read16Bits(memory, pcValue);
    spy.doContinue();

    return fetchedAddress;
  }

  public int cyclesCost() {
    return 3 + 3;
  }

  public String toString() {
    return Helper.convertToHex(read()) + "";
  }

  public int getLength() {
    return 2;
  }

  public Object clone() throws CloneNotSupportedException {
    T lastFetchedAddress = fetchedAddress;
    return new Memory16BitReference(memory, pc, delta, spy) {
      public T read() {
        return lastFetchedAddress;
      }
    };
  }
}
