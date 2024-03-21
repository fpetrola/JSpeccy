package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;
import com.fpetrola.z80.registers.flag.AluOperationsInitializer;

public class Cpd<T extends WordNumber> extends Cpi<T> {
  public Cpd(Register<T> a, Register flag, RegisterPair<T> bc, Register<T> hl, Memory<T> memory, IO<T> io) {
    super(a, flag, bc, hl, memory, io);
  }

  protected void flagOperation() {
    T value = memory.read(hl.read());
    T reg_A = a.read();
    AluOperationsInitializer.cpdTableAluOperation.executeWithCarry2(value, reg_A, WordNumber.createValue(bc.read().isNotZero() ? 1 : 0), flag);
  }

  protected void next() {
    hl.decrement();
  }
}
