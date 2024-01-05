package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.Memory;

public final class MemorySpy implements Memory {
  private Memory memory;
  private OpcodesSpy spy;

  public MemorySpy(Memory memory, OpcodesSpy spy) {
    this.memory = memory;
    this.spy = spy;
  }

  public void write(int address, int value) {
    if (spy.isCapturing())
      spy.addWriteMemoryReference(address, value);

    memory.write(address, value);
  }

  public int read(int address) {
    int value = memory.read(address);

    if (spy.isCapturing())
      spy.addReadMemoryReference(address, value);
    
    return value;
  }
}