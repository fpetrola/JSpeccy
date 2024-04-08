package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.RepeatingInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Flags;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;

public class Cpir<T extends WordNumber> extends RepeatingInstruction<T> {
  private final Register<T> flag;
  private final Register<T> bc;

  public Cpir(Register<T> flag, RegisterPair<T> bc, ImmutableOpcodeReference<T> pc, Cpi cpi) {
    super(cpi, pc, bc);
    this.flag = flag;
    this.bc = bc;
  }

  protected boolean checkLoopCondition() {
    return !((flag.read().intValue() & Flags.ZERO_FLAG) != 0) && bc.read().isNotZero();
  }
}
