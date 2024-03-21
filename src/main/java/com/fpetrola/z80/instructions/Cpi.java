package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.BlockInstruction;
import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;
import com.fpetrola.z80.registers.flag.TableFlagRegisterInitTables;

public class Cpi<T extends WordNumber> extends BlockInstruction<T> {
  protected Register<T> a;

  public Cpi(Register<T> a, Register<T> flag, RegisterPair<T> bc, Register<T> hl, Memory<T> memory, IO<T> io) {
    super(bc, hl, flag, memory, io);
    this.a = a;
  }

  public int execute() {
    bc.decrement();
    flagOperation();
    next();
    return 1;
  }

  protected void flagOperation() {
    T value = memory.read(hl.read());
    T reg_A = a.read();
    TableFlagRegisterInitTables.cpiTableAluOperation.executeWithCarry2(value, reg_A, WordNumber.createValue(bc.read().isNotZero() ? 1 : 0), flag);
  }
}
