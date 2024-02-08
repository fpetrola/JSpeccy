package com.fpetrola.z80.mmu;

import com.fpetrola.z80.jspeccy.MemoryWriteListener;

public interface Memory<T> {

  T read(T address);

  void write(T address, T value);
  boolean compare();

  void update();

  void setMemoryWriteListener(MemoryWriteListener memoryWriteListener);

  Memory getMemory();
}
