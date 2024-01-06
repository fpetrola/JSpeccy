package z80core;

import com.fpetrola.z80.mmu.IO;

final class IOImplementation implements IO {
  private MemIoOps memIoOps;

  public IOImplementation(MemIoOps memory) {
    this.memIoOps = memory;
  }

  public void out(int port, int value) {
    memIoOps.outPort(port, value);
  }

  public int in(int port) {
    return memIoOps.inPort(port);
  }
}