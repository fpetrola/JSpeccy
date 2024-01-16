package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.instructions.base.AbstractInstruction;

public class RRD extends AbstractInstruction {

  public RRD(State state) {
    super(state);
  }

  public int execute() {
    int reg_A = a.read();
    r.increment(1);

    int temp = memory.read(hl.read());
    int nibble1 = (reg_A & 0x00F0) >> 4;
    int nibble2 = reg_A & 0x000F;
    int nibble3 = (temp & 0x00F0) >> 4;
    int nibble4 = temp & 0x000F;
    reg_A = (nibble1 << 4) | nibble4;
    temp = (nibble2 << 4) | nibble3;
    memory.write(hl.read(), temp);

    flag.RRD(reg_A);

    return 1;
  }
}
