package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.DefaultTargetFlagInstruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class CCF<T extends WordNumber> extends DefaultTargetFlagInstruction<T> {
  public CCF(FlagRegister flag, Register<T> a) {
    super(a, flag);
  }

  public int execute() {
    flag.CCF(target.read());
    return 4;
  }
}
