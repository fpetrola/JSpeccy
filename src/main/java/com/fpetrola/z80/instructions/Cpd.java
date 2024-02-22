package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;
import com.fpetrola.z80.registers.flag.IFlagRegister;

import java.util.function.Consumer;

public class Cpd<T extends WordNumber> extends Cpi<T> {
  Cpd(Register<T> a, IFlagRegister flag, RegisterPair<T> bc, Register<T> hl, Memory<T> memory, IO<T> io) {
    super(a, flag, bc, hl, memory, io);
  }

  protected void flagOperation() {
    flag.CPD(memory.read(hl.read()), a.read(), bc.read());
  }

  protected Consumer<Register> getNextOperation() {
    return Register::decrement;
  }
}
