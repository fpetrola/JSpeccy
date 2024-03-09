package com.fpetrola.z80.opcodes.references;

public interface ImmutableOpcodeReference<T> extends PublicCloneable {
  T read();
  int getLength();
}
