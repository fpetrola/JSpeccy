package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.IFlagRegister;

import static com.fpetrola.z80.registers.RegisterName.F;
import static com.fpetrola.z80.registers.RegisterName.HL;

public class Nop<T extends WordNumber> extends AbstractInstruction<T> {
  public Nop(State state) {
    super(state, state.getRegister(HL), (IFlagRegister) state.getRegister(F));
  }

  public int execute() {
    return 4;
  }
}
