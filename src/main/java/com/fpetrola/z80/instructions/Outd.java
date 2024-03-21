package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;
import com.fpetrola.z80.registers.flag.FlagRegister;
import com.fpetrola.z80.registers.flag.TableFlagRegisterInitTables;

public class Outd<T extends WordNumber> extends Outi<T> {
  public Outd(RegisterPair<T> bc, Register<T> hl, FlagRegister<T> flag, Memory<T> memory, IO<T> io) {
    super(bc, hl, flag, memory, io);
  }

  protected void flagOperation() {
    TableFlagRegisterInitTables.iniTableAluOperation.executeWithCarry(bc.getHigh().read(), flag);
  }

  protected void next() {
    hl.decrement();
  }
}
