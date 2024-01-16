package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.OOZ80;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.Register;

public class Memory8BitReference implements OpcodeReference {

  private final Memory memory;
  private int delta;
  private int fetchedValue;
  private Register pc;

  public Memory8BitReference(Memory memory, Register pc, int delta) {
    this.memory = memory;
    this.pc = pc;
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
    return pc.read();
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

  public Object clone() throws CloneNotSupportedException {
    int lastFetchedValue = fetchedValue;
    return new Memory8BitReference(memory, pc, delta) {
      public int read() {
        return lastFetchedValue;
      }
    };
  }
}
