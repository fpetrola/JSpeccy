package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.Register;

public class Memory8BitReference<T extends WordNumber> implements ImmutableOpcodeReference<T> {

  private final Memory<T> memory;
  private int delta;
  private T fetchedValue;
  private Register<T> pc;

  public Memory8BitReference(Memory memory, Register pc, int delta) {
    this.memory = memory;
    this.pc = pc;
    this.delta = delta;
  }

  public T read() {
    fetchValue();
    return fetchedValue;
  }

  private void fetchValue() {
    fetchedValue = memory.read(fetchAddress().plus(delta));
  }

  public void write(T value) {
    memory.write(fetchAddress(), value);
  }

  private T fetchAddress() {
    return pc.read();
  }

  public String toString() {
    T read = read();
    return read == null ? "" : "0x" + Helper.convertToHex(read.intValue()) + "";
  }

  public int getLength() {
    return 1;
  }

  public Object clone() throws CloneNotSupportedException {
    T lastFetchedValue = fetchedValue;
    return new Memory8BitReference(memory, pc, delta) {
      public T read() {
        return lastFetchedValue;
      }
    };
  }
}
