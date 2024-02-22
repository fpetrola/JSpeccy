package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.IO;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;
import com.fpetrola.z80.registers.flag.IFlagRegister;

public class Ini<T extends WordNumber> extends BlockInstruction<T> {
  Ini(RegisterPair<T> bc, Register<T> hl, IFlagRegister<T> flag, Memory<T> memory, IO<T> io) {
    super(bc, hl, flag, memory, io);
  }

  public int execute() {
    T cValue = bc.getLow().read();
    T in = io.in(cValue);
    T hlValue = hl.read();
    memory.write(hlValue, in);
    bc.getHigh().decrement();
    forward();
    flagOperation();
    return 1;
  }

  protected void flagOperation() {
    flag.INI(bc.getHigh().read());
  }
}
