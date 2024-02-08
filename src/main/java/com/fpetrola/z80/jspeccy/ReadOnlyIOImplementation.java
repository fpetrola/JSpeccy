package com.fpetrola.z80.jspeccy;

import com.fpetrola.z80.cpu.OOZ80;
import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.opcodes.references.WordNumber;
import z80core.MemIoOps;

public class ReadOnlyIOImplementation<T extends WordNumber> implements IO<T> {
  private IO<T> io;

  public ReadOnlyIOImplementation(IO<T> io) {
    this.io = io;
  }

  public T in(T port) {
    return OOZ80.createValue(0);
  }

  public void out(T port, T value) {

  }
}