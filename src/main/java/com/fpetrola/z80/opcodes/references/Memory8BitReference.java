package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.Register;

public class Memory8BitReference<T extends WordNumber> implements ImmutableOpcodeReference<T> {

  private final Memory<T> memory;
  private int delta;
  protected T fetchedAddress;
  private Register<T> pc;

  public Memory8BitReference(Memory memory, Register pc, int delta) {
    this.memory = memory;
    this.pc = pc;
    this.delta = delta;
  }

  public T read() {
    return memory.read(fetchAddress().plus(delta));
  }

  public void write(T value) {
    memory.write(fetchAddress(), value);
  }

  protected T fetchAddress() {
    return fetchedAddress = pc.read();
  }

  public String toString() {
    T read = read();
    return read == null ? "" : "0x" + Helper.convertToHex(read.intValue()) + "";
  }

  public int getLength() {
    return 1;
  }

  public Object clone() throws CloneNotSupportedException {
    return new MyMemory8BitReference(fetchedAddress, memory, pc, delta);
  }

  private class MyMemory8BitReference extends Memory8BitReference<T> {
    public MyMemory8BitReference(T lastFetchedAddress, Memory<T> memory, Register<T> pc, int delta) {
      super(memory, pc, delta);
      this.fetchedAddress = lastFetchedAddress;
    }

    @Override
    protected T fetchAddress() {
      return fetchedAddress;
    }
  }
}
