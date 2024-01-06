package com.fpetrola.z80.instructions;

import java.util.HashMap;
import java.util.Map;

import com.fpetrola.z80.mmu.Memory;

public final class MemorySpy implements Memory {
  private Memory memory;
  private OpcodesSpy spy;
  private Map<Integer, Integer> map = new HashMap<Integer, Integer>();

  public MemorySpy(Memory memory, OpcodesSpy spy) {
    this.memory = memory;
    this.spy = spy;
  }

  public void write(int address, int value) {
    int key = address & 0xFFFF;
    Integer times = map.get(key);
    if (times != null)
      times = times + 1;
    else
      times = 0;

    map.put(key, times);

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

  int getAddressModificationsCounter(int address) {
    return map.get(address & 0xFFFF);
  }

  public boolean compare() {
    return memory.compare();
  }

  @Override
  public void update() {
    memory.update();
  }
}