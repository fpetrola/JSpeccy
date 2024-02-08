package com.fpetrola.z80.instructions;

import com.fpetrola.z80.cpu.OOZ80;
import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class RST<T extends WordNumber> extends AbstractInstruction<T> {

  private final int p;

  public RST(State state, int p) {
    super(state);
    this.p = p;
  }

  public int execute() {
    Push.doPush(pc.read().plus(1), sp, memory);
    setNextPC(OOZ80.createValue(p & 0xFFFF));
    return 5 + 3 + 3;
  }

  public String toString() {
    return "RST " + String.format("%02X", p);
  }

  public int getP() {
    return p;
  }
}
