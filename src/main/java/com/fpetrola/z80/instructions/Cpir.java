package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.RepeatingInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class Cpir<T extends WordNumber> extends RepeatingInstruction<T> {
  private final FlagRegister<T> flag;
  private final Register<T> bc;

  public Cpir(FlagRegister<T> flag, Register<T> bc, ImmutableOpcodeReference<T> pc, Register<T> b, Cpi cpi) {
    super(cpi, pc, b, bc);
    this.flag = flag;
    this.bc = bc;
  }

  protected boolean checkLoopCondition() {
    return !flag.getZ() && bc.read().isNotZero();
  }
}
