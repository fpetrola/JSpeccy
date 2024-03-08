package com.fpetrola.z80.instructions;

import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;

public class DummyImmutableOpcodeReference<T> implements ImmutableOpcodeReference<T> {
  public T read() {
    return null;
  }

  public int getLength() {
    return 0;
  }

  public Object clone() throws CloneNotSupportedException {
    return null;
  }
}
