package com.fpetrola.z80.jspeccy;

public interface MemoryWriteListener {
  void writtingMemoryAt(int address, int value);
}
