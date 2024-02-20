package com.fpetrola.z80.opcodes.references;

public interface OpcodeReference<T> extends BaseImmutableOpcodeReference<T> {
  void write(T value);
  T read();
}
