package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.IFlagRegister;

public class Cpi<T extends WordNumber> extends AbstractInstruction<T> {
  private Register<T> a;
  private IFlagRegister<T> flag;
  private Register<T> bc;
  private Register<T> hl;
  private Memory<T> memory;

  Cpi(Register<T> a, IFlagRegister<T> flag, Register<T> bc, Register<T> hl, Memory<T> memory) {
    this.a = a;
    this.flag = flag;
    this.bc = bc;
    this.hl = hl;
    this.memory = memory;
  }

  public int execute() {
    T hlValue = hl.read();
    T valueFromHL = memory.read(hlValue);
    hl.increment();
    bc.decrement();
    flag.CPI(valueFromHL, a.read(), bc.read());
    return 1;
  }
}
