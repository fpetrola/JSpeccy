package com.fpetrola.z80.opcodes.references;

public interface ImmutableOpcodeReference<T> {
  T read();

  int getLength();

  Object clone() throws CloneNotSupportedException;
}
