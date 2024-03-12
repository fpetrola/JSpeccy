package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class SCF<T extends WordNumber> extends AbstractInstruction<T> {
  private final FlagRegister<T> flag;

  public SCF(FlagRegister<T> flag) {
    this.flag = flag;
  }

  public int execute() {
    flag.SCF();
    return 4;
  }
}
