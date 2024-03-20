package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;
import com.fpetrola.z80.registers.flag.FlagRegister;

public abstract class BlockInstruction<T extends WordNumber> extends AbstractInstruction<T> {
  protected final RegisterPair<T> bc;
  protected final Register<T> hl;
  protected final FlagRegister<T> flag;
  protected final Memory<T> memory;
  protected final IO<T> io;

  public BlockInstruction(RegisterPair<T> bc, Register<T> hl, FlagRegister<T> flag, Memory<T> memory, IO<T> io) {
    this.bc = bc;
    this.hl = hl;
    this.flag = flag;
    this.memory = memory;
    this.io = io;
  }

  protected abstract void flagOperation();

  protected void next() {
    hl.increment();
  }
}
