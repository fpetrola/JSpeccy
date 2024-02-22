package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;
import com.fpetrola.z80.registers.flag.IFlagRegister;

import java.util.function.Consumer;

public class Ldi<T extends WordNumber> extends BlockInstruction<T> {
  protected final Register<T> de;

  Ldi(RegisterPair<T> bc, Register<T> de, Register<T> hl, IFlagRegister<T> flag, Memory<T> memory, IO<T> io) {
    super(bc, hl, flag, memory, io);
    this.de = de;
  }

  public int execute() {
    T hlValue = hl.read();
    T deValue = de.read();
    T work8 = memory.read(hlValue);
    memory.write(deValue, work8);

    forward();
    bc.decrement();

    flagOperation();

    return 1;
  }

  protected void flagOperation() {
    flag.LDI(bc.read());
  }

  protected void forward() {
    Consumer<Register> nextOperation = getNextOperation();
    nextOperation.accept(hl);
    nextOperation.accept(de);
  }
}
