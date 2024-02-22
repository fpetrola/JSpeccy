package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.IFlagRegister;

import static com.fpetrola.z80.registers.RegisterName.F;
import static com.fpetrola.z80.registers.RegisterName.HL;

public class Outi<T extends WordNumber> extends AbstractInstruction<T> {
  private final Register<T> b;
  private final Register<T> c;
  private final Register<T> hl;
  private final IFlagRegister<T> flag;
  private final Memory<T> memory;
  private final IO<T> io;

  Outi(State state, Register<T> b, Register<T> c, Register<T> hl, IFlagRegister<T> flag, Memory<T> memory, IO<T> io) {
    super(state, state.getRegister(HL), (IFlagRegister) state.getRegister(F));
    this.b = b;
    this.c = c;
    this.hl = hl;
    this.flag = flag;
    this.memory = memory;
    this.io = io;
  }

  public int execute() {
    T hlValue = hl.read();
    T valueFromHL = memory.read(hlValue);

    T cValue = c.read();

    io.out(cValue, valueFromHL);

    hl.increment();
    b.decrement();

    flag.OUTI(b.read());

    return 1;
  }
}
