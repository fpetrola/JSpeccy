package com.fpetrola.z80.opcodes.references;

public interface MutableOpcodeReference<T> extends PublicCloneable {
  void write(T value);

}
