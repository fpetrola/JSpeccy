package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.IFlagRegister;

import static com.fpetrola.z80.registers.RegisterName.F;
import static com.fpetrola.z80.registers.RegisterName.HL;

public class Ldi<T extends WordNumber> extends AbstractInstruction<T> {

  public Ldi(State state) {
    super(state, state.getRegister(HL), (IFlagRegister) state.getRegister(F));
  }

  public int execute() {
    T hlValue = hl.read();
    T deValue = de.read();

    T work8 = memory.read(hlValue);
    memory.write(deValue, work8);

    hl.increment();
    de.increment();
    bc.decrement();

    flag.LDI(bc.read());

    return 1;
  }
}
