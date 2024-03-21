package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.RepeatingInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Flags;
import com.fpetrola.z80.registers.Register;

public class Cpir<T extends WordNumber> extends RepeatingInstruction<T> {
  private final Register<T> flag;
  private final Register<T> bc;

  public Cpir(Register<T> flag, Register<T> bc, ImmutableOpcodeReference<T> pc, Register<T> b, Cpi cpi) {
    super(cpi, pc, b, bc);
    this.flag = flag;
    this.bc = bc;
  }

  protected boolean checkLoopCondition() {
    return !((flag.read().intValue() & Flags.ZERO_FLAG) != 0) && bc.read().isNotZero();
  }
}
