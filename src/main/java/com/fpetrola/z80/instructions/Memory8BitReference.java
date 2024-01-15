package com.fpetrola.z80.instructions;

import com.fpetrola.z80.OOZ80;
import com.fpetrola.z80.mmu.Memory;

public class Memory8BitReference implements OpcodeReference {

  private final Memory memory;
  private int delta;
  private OpCode opCode;
  private int fetchedValue;

  public Memory8BitReference(Memory memory) {
    this.memory = memory;
  }

  public Memory8BitReference(Memory memory, int delta) {
    this(memory);
    this.delta = delta;
  }

  public int read() {
    fetchValue();
    return fetchedValue;
  }

  private void fetchValue() {
    fetchedValue = memory.read(fetchAddress() + delta);
  }

  public void write(int value) {
    memory.write(fetchAddress(), value);
  }

  private int fetchAddress() {
    return opCode.getPC().read();
  }

  public int cyclesCost() {
    return 3;
  }

  public String toString() {
    return OOZ80.convertToHex(read()) + "";
  }

  public int getLength() {
    return 1;
  }

  public void setOpCode(OpCode opCode) {
    this.opCode = opCode;
  }

  public Object clone() throws CloneNotSupportedException {
    int lastFetchedValue = fetchedValue;
    return new Memory8BitReference(memory, delta) {
      public int read() {
        return lastFetchedValue;
      }
    };
  }
}
