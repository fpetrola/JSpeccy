package com.fpetrola.z80.opcodes.references;

public interface BaseImmutableOpcodeReference<T> extends Cloneable {
  int getLength();

  Object clone() throws CloneNotSupportedException;
}
