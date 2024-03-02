package com.fpetrola.z80.instructions;

import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class Cpdr<T extends WordNumber> extends RepeatingInstruction<T> {
  private final FlagRegister<T> flag;

  Cpdr(ImmutableOpcodeReference<T> pc, Register<T> b, Register<T> bc, FlagRegister<T> flag, Cpd cpd) {
    super(cpd, pc, b, bc);
    this.flag = flag;
  }

  protected boolean checkLoopCondition() {
    return !flag.getZ() && bc.read().isNotZero();
  }
}
