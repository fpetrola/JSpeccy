package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;
import com.fpetrola.z80.registers.flag.IFlagRegister;

public class Cpi<T extends WordNumber> extends BlockInstruction<T> {
  protected Register<T> a;

  Cpi(Register<T> a, IFlagRegister<T> flag, RegisterPair<T> bc, Register<T> hl, Memory<T> memory, IO<T> io) {
    super(bc, hl, flag, memory, io);

    this.a = a;
  }

  public int execute() {
    bc.decrement();
    flagOperation();
    forward();
    return 1;
  }

  protected void flagOperation() {
    flag.CPI(memory.read(hl.read()), a.read(), bc.read());
  }
}
