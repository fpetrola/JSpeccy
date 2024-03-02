package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.IO;

public class MockedIO implements IO {
  public Object in(Object port) {
    return null;
  }

  public void out(Object port, Object value) {
  }
}
