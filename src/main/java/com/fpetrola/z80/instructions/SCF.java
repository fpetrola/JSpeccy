package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.IFlagRegister;

import static com.fpetrola.z80.registers.RegisterName.F;
import static com.fpetrola.z80.registers.RegisterName.HL;

public class SCF<T extends WordNumber> extends AbstractInstruction<T> {
  private final IFlagRegister<T> flag;

  SCF(IFlagRegister<T> flag) {
    this.flag = flag;
  }

  public int execute() {
    flag.SCF();
    return 4;
  }
}
