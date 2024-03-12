package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class Cpd<T extends WordNumber> extends Cpi<T> {
  public Cpd(Register<T> a, FlagRegister flag, RegisterPair<T> bc, Register<T> hl, Memory<T> memory, IO<T> io) {
    super(a, flag, bc, hl, memory, io);
  }

  protected void flagOperation() {
    flag.CPD(memory.read(hl.read()), a.read(), bc.read());
  }

  protected void next() {
    hl.decrement();
  }
}
