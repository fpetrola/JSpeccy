package com.fpetrola.z80.spy;

import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.OpcodeReference;

public class ReadMemoryOpcodeReference implements OpcodeReference {
  protected Memory memory;
  protected int address;

  public ReadMemoryOpcodeReference(Memory memory, int address) {
    this.memory = memory;
    this.address = address;
  }

  public void write(int value) {
    memory.write(address, value);
  }

  public int read() {
    return memory.read(address);
  }

  public int getLength() {
    return 0;
  }

  public int cyclesCost() {
    return 0;
  }

  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }
}