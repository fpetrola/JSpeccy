package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class MockedIO implements IO {
  public Object in(Object port) {
    return WordNumber.createValue(123);
  }

  public void out(Object port, Object value) {
  }
}
