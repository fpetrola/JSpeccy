package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.IFlagRegister;

import static com.fpetrola.z80.registers.RegisterName.F;
import static com.fpetrola.z80.registers.RegisterName.HL;

public class Ind<T extends WordNumber> extends AbstractInstruction<T> {

  public Ind(State<T> state) {
    super(state, state.getRegister(HL), (IFlagRegister) state.getRegister(F));
  }

  public int execute() {
    T cValue = c.read();
    T in = state.getIo().in(cValue);

    T hlValue = hl.read();

    memory.write(hlValue, in);

    b.decrement();
    hl.decrement();

    flag.IND(b.read());
    return 1;
  }
}
