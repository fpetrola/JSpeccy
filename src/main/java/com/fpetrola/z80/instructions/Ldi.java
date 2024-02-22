package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.IFlagRegister;

public class Ldi<T extends WordNumber> extends AbstractInstruction<T> {
  private final Register<T> bc;
  private final Register<T> de;
  private final Register<T> hl;
  private final IFlagRegister<T> flag;
  private final Memory<T> memory;

  Ldi(Register<T> bc, Register<T> de, Register<T> hl, IFlagRegister<T> flag, Memory<T> memory) {
    this.bc = bc;
    this.de = de;
    this.hl = hl;
    this.flag = flag;
    this.memory = memory;
  }

  public int execute() {
    T hlValue = hl.read();
    T deValue = de.read();

    T work8 = memory.read(hlValue);
    memory.write(deValue, work8);

    hl.increment();
    de.increment();
    bc.decrement();

    flag.LDI(bc.read());

    return 1;
  }
}
