package com.fpetrola.z80.opcodes.references;

public interface BaseImmutableOpcodeReference<T> extends Cloneable {
  T read();

  int getLength();

  Object clone() throws CloneNotSupportedException;
}
