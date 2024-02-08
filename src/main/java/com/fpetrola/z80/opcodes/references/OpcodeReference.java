package com.fpetrola.z80.opcodes.references;

public interface OpcodeReference<T> extends Cloneable {
  T read();

  void write(T value);

  int cyclesCost();

  int getLength();

  Object clone() throws CloneNotSupportedException;
}
