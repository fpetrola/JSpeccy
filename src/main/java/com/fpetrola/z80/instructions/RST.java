package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.instructions.base.AbstractInstruction;

public class RST extends AbstractInstruction {

  private final int p;

  public RST(State state, int p) {
    super(state);
    this.p = p;
  }

  public int execute() {
    final int position = p & 0xFFFF;
    sp.decrement(2);
    final int address = sp.read();
    int value = pc.read() + 1;
    memory.write(address, value & 0xFF);
    memory.write(address + 1, (value >> 8));

    state.setNextPC(position);
//    pc.write(position);

    return 5 + 3 + 3;
  }

  public String toString() {
    return "RST " + String.format("%02X", p);
  }

  public int getP() {
    return p;
  }
}
