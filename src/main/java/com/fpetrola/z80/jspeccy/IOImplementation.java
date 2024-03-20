package com.fpetrola.z80.jspeccy;

import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.opcodes.references.WordNumber;
import z80core.MemIoOps;

final class IOImplementation<T extends WordNumber> implements IO<T> {
  private MemIoOps memIoOps;

  public IOImplementation(MemIoOps memory) {
    this.memIoOps = memory;
  }

  public void out(T port, T value) {
    memIoOps.outPort(port.intValue(), value.intValue());
  }
  public T in(T port) {
    return WordNumber.createValue(memIoOps.inPort(port.intValue()));
  }
}