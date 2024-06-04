package com.fpetrola.z80.jspeccy;

import com.fpetrola.z80.mmu.Memory;

public class ReadOnlyMemoryImplementation<T> implements Memory<T> {
  protected Memory<T> memory;

  public ReadOnlyMemoryImplementation(Memory memory) {
    this.memory = memory;
  }

  public T read(T address) {
    return memory.read(address);
  }

  public void write(T address, T value) {
  }

  public boolean compare() {
    return memory.compare();
  }

  public void update() {
  }

  public void addMemoryWriteListener(MemoryWriteListener memoryWriteListener) {
  }

  @Override
  public void removeMemoryWriteListener(MemoryWriteListener memoryWriteListener) {

  }

  public Memory getMemory() {
    return this;
  }

  @Override
  public void reset() {

  }

  @Override
  public void addMemoryReadListener(MemoryReadListener memoryReadListener) {

  }

  @Override
  public void removeMemoryReadListener(MemoryReadListener memoryReadListener) {

  }
}