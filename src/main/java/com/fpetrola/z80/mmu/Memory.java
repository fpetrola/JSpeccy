package com.fpetrola.z80.mmu;

import com.fpetrola.z80.jspeccy.MemoryWriteListener;

public interface Memory {

  int read(int address);

  void write(int address, int value);
  boolean compare();

  void update();

  void setMemoryWriteListener(MemoryWriteListener memoryWriteListener);
}
