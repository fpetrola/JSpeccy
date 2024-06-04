package com.fpetrola.z80.jspeccy;

import com.fpetrola.z80.opcodes.references.WordNumber;

public interface MemoryWriteListener<T extends WordNumber> {
  void writtingMemoryAt(T address, T value);
}
