package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.IFlagRegister;

import static com.fpetrola.z80.registers.RegisterName.F;
import static com.fpetrola.z80.registers.RegisterName.HL;

public class RST<T extends WordNumber> extends AbstractInstruction<T> {

  private final int p;

  public RST(State state, int p) {
    super(state, state.getRegister(HL), (IFlagRegister) state.getRegister(F));
    this.p = p;
  }

  public int execute() {
    Push.doPush(pc.read().plus1(), state.getRegisterSP(), state.getMemory());
    setNextPC(WordNumber.createValue(p & 0xFFFF));
    return 5 + 3 + 3;
  }

  public String toString() {
    return "RST " + String.format("%02X", p);
  }

  public int getP() {
    return p;
  }
}
