package com.fpetrola.z80.spy;

import com.fpetrola.z80.jspeccy.MemoryReadListener;
import com.fpetrola.z80.jspeccy.MemoryWriteListener;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;

import java.util.HashMap;
import java.util.Map;

public final class MemorySpy<T extends WordNumber> implements Memory<T> {
  private Memory<T> memory;

  public Map<Integer, Integer> map = new HashMap<Integer, Integer>();

  public MemorySpy() {
  }

  public MemorySpy(Memory<T> memory) {
    this.memory = memory;
  }

  public void write(T address, T value) {
    int key = address.intValue() & 0xFFFF;
    Integer times = map.get(key);
    if (times != null)
      times = times + 1;
    else
      times = 0;

    map.put(key, times);
    memory.write(address, value);
  }

  public T read(T address) {
    T value = memory.read(address);
    return value;
  }

  public int getAddressModificationsCounter(int address) {
    Integer integer = map.get(address & 0xFFFF);
    return integer != null ? integer : 0;
  }

  public boolean compare() {
    return memory.compare();
  }

  @Override
  public void update() {
    memory.update();
  }

  @Override
  public void addMemoryWriteListener(MemoryWriteListener memoryWriteListener) {
    memory.addMemoryWriteListener(memoryWriteListener);
  }

  @Override
  public void removeMemoryWriteListener(MemoryWriteListener memoryWriteListener) {
    memory.removeMemoryWriteListener(memoryWriteListener);
  }

  public void setMemory(Memory memory) {
    this.memory = memory;
  }

  public Memory getMemory() {
    return memory;
  }

  @Override
  public void reset() {
    memory.reset();
  }

  @Override
  public void addMemoryReadListener(MemoryReadListener memoryReadListener) {
    memory.addMemoryReadListener(memoryReadListener);
  }

  @Override
  public void removeMemoryReadListener(MemoryReadListener memoryReadListener) {
    memory.removeMemoryReadListener(memoryReadListener);
  }
}