package com.fpetrola.z80.jspeccy;

public interface MemoryWriteListener<T> {
  void writtingMemoryAt(T address, T value);
}
