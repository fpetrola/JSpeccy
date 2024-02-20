package com.fpetrola.z80.opcodes.references;

public interface MutableOpcodeReference<T> extends Cloneable {
  void write(T value);

  Object clone() throws CloneNotSupportedException;
}
