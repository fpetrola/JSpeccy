package com.fpetrola.z80.jspeccy;

public interface MemoryReadListener<T> {
  void readingMemoryAt(T address, T value);
}
