package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class CCF<T extends WordNumber> extends AbstractInstruction<T> {
  private final FlagRegister flag;
  private final Register<T> a;

  CCF(FlagRegister flag, Register<T> a) {
    this.flag = flag;
    this.a = a;
  }

  public int execute() {
    flag.CCF(a.read());
    return 4;
  }
}
