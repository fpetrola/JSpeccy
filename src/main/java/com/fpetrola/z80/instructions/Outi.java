package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class Outi<T extends WordNumber> extends BlockInstruction<T> {
  Outi(RegisterPair<T> bc, Register<T> hl, FlagRegister<T> flag, Memory<T> memory, IO<T> io) {
    super(bc, hl, flag, memory, io);
  }

  public int execute() {
    T hlValue = hl.read();
    T cValue = bc.getLow().read();
    T valueFromHL = memory.read(hlValue);
    io.out(cValue, valueFromHL);
    next();
    bc.getHigh().decrement();
    flagOperation();

    return 1;
  }

  protected void flagOperation() {
    flag.OUTI(bc.getHigh().read());
  }
}
