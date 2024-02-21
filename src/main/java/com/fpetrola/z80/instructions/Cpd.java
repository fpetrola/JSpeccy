package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.IFlagRegister;

public class Cpd<T extends WordNumber> extends AbstractInstruction<T> {
  private Register<T> a;
  private IFlagRegister flag;
  private Register<T> bc;
  private Register<T> hl;
  private Memory<T> memory;

  Cpd(Register<T> a, IFlagRegister flag, Register<T> bc, Register<T> hl, Memory<T> memory) {
    this.a = a;
    this.flag = flag;
    this.bc = bc;
    this.hl = hl;
    this.memory = memory;
  }

  public int execute() {
    T hlValue = hl.read();
    T valueFromHL = memory.read(hlValue);
    hl.decrement();
    bc.decrement();
    flag.CPD(valueFromHL, a.read(), bc.read());
    return 1;
  }
}
