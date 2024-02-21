package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.IFlagRegister;

public class CCF<T extends WordNumber> extends AbstractInstruction<T> {
  private final IFlagRegister flag;
  private final Register<T> a;

  public CCF(IFlagRegister flag, Register<T> a) {
    this.flag = flag;
    this.a = a;
  }

  public int execute() {
    flag.CCF(a.read());
    return 4;
  }
}
