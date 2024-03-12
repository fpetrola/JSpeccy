package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class Ldd<T extends WordNumber> extends Ldi<T> {
  public Ldd(Register<T> de, RegisterPair<T> bc, Register<T> hl, FlagRegister<T> flag, Memory<T> memory, IO<T> io) {
    super(de, bc, hl, flag, memory, io);
  }

  protected void flagOperation() {
    flag.LDD(bc.read());
  }

  protected void next() {
    hl.decrement();
    de.decrement();
  }
}
