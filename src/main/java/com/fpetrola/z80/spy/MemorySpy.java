package com.fpetrola.z80.spy;

import java.util.HashMap;
import java.util.Map;

import com.fpetrola.z80.jspeccy.MemoryReadListener;
import com.fpetrola.z80.jspeccy.MemoryWriteListener;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;

public final class MemorySpy<T extends WordNumber> implements Memory<T> {
  private Memory<T> memory;

  private InstructionSpy<T> spy;
  public Map<Integer, Integer> map = new HashMap<Integer, Integer>();

  public MemorySpy() {
  }

  public MemorySpy(Memory<T> memory, InstructionSpy<T> spy) {
    this.memory = memory;
    this.spy = spy;
  }

  public void write(T address, T value) {
    int key = address.intValue() & 0xFFFF;
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

  public T read(T address) {
    T value = memory.read(address);

    if (spy.isCapturing())
      spy.addReadMemoryReference(address, value);

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
  public void setMemoryWriteListener(MemoryWriteListener memoryWriteListener) {
    memory.setMemoryWriteListener(memoryWriteListener);
  }

  public void setMemory(Memory memory) {
    this.memory = memory;
  }

  @Override
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