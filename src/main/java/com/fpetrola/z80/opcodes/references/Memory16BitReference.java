package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.mmu.Memory;

public class Memory16BitReference<T extends WordNumber> implements OpcodeReference<T> {

  private final Memory<T> memory;
  protected T fetchedAddress;
  private ImmutableOpcodeReference<T> pc;
  private int delta;

  public Memory16BitReference(Memory memory, ImmutableOpcodeReference pc, int delta) {
    this.memory = memory;
    this.pc = pc;
    this.delta = delta;
  }

  public T read() {
    return fetchAddress();
  }

  public void write(T value) {
    T address = fetchAddress();
    Memory.write16Bits(memory, value, address);
  }

  protected T fetchAddress() {
    memory.disableReadListener();
    T pcValue = pc.read().plus(delta);
    fetchedAddress = Memory.read16Bits(memory, pcValue);
    memory.enableReadListener();

    return fetchedAddress;
  }

  public String toString() {
    T read = read();
    return read == null ? "" : "0x" + Helper.convertToHex(read.intValue()) + "";
  }

  public int getLength() {
    return 2;
  }

  public Object clone() throws CloneNotSupportedException {
    T lastFetchedAddress = fetchedAddress;
    return new MyMemory16BitReference(lastFetchedAddress, memory, pc, delta);
  }

  private class MyMemory16BitReference extends Memory16BitReference<T> {
    public MyMemory16BitReference(T lastFetchedAddress, Memory<T> memory, ImmutableOpcodeReference<T> pc, int delta) {
      super(memory, pc, delta);
      this.fetchedAddress = lastFetchedAddress;
    }

    protected T fetchAddress() {
      return fetchedAddress;
    }
  }
}
