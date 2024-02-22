package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.IFlagRegister;

public class Ind<T extends WordNumber> extends AbstractInstruction<T> {
  private final Register<T> b;
  private final Register<T> c;
  private final Register<T> hl;
  private final IFlagRegister<T> flag;
  private final Memory<T> memory;
  private final IO<T> io;

  Ind(Register<T> b, Register<T> c, Register<T> hl, IFlagRegister<T> flag, Memory<T> memory, IO<T> io) {
    this.b = b;
    this.c = c;
    this.hl = hl;
    this.flag = flag;
    this.memory = memory;
    this.io = io;
  }

  public int execute() {
    T cValue = c.read();
    T in = io.in(cValue);

    T hlValue = hl.read();

    memory.write(hlValue, in);

    b.decrement();
    hl.decrement();

    flag.IND(b.read());
    return 1;
  }
}
