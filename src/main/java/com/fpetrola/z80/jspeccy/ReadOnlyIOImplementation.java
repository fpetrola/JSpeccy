package com.fpetrola.z80.jspeccy;

import com.fpetrola.z80.mmu.IO;
import z80core.MemIoOps;

public class ReadOnlyIOImplementation implements IO {
  private IO io;

  public ReadOnlyIOImplementation(IO io) {
    this.io = io;
  }

  public int in(int port) {
    return 0;
  }
  public void out(int port, int value) {

  }
}