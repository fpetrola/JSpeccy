package com.fpetrola.z80.spy;

public interface RegisterWriteListener<T> {
  void writingRegister(T value, boolean isIncrement);
}
