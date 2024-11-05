package com.fpetrola.z80.spy;

public interface RegisterReadListener<T> {
  void readingRegister(T value);
}
