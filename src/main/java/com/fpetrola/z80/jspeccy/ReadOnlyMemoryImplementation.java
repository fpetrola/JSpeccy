package com.fpetrola.z80.jspeccy;

import com.fpetrola.z80.mmu.Memory;
import z80core.MemIoOps;

public class ReadOnlyMemoryImplementation implements Memory {
  protected Memory memory;

  public ReadOnlyMemoryImplementation(Memory memory) {
    this.memory = memory;
  }

  public int read(int address) {
    return memory.read(address);
  }

  public void write(int address, int value) {
  }

  public boolean compare() {
    return memory.compare();
  }

  public void update() {
  }

  public void setMemoryWriteListener(MemoryWriteListener memoryWriteListener) {
  }

  public Memory getMemory() {
    return this;
  }
}