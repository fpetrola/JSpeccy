package com.fpetrola.z80.opcodes.references;

public interface ImmutableOpcodeReference<T> extends BaseImmutableOpcodeReference<T> {
  T read();

  int getLength();

  Object clone() throws CloneNotSupportedException;
}
