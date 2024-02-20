package com.fpetrola.z80.opcodes.references;

public interface OpcodeReference<T> extends ImmutableOpcodeReference<T> {
  void write(T value);
}
