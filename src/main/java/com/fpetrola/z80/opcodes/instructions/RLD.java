package com.fpetrola.z80.opcodes.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.opcodes.references.AbstractInstruction;

public class RLD extends AbstractInstruction {

  public RLD(State state) {
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
    reg_A = (nibble1 << 4) | nibble3;
    temp = (nibble4 << 4) | nibble2;

    memory.write(hl.read(), temp);
    flag.RLD(reg_A);

    return 1;
  }
}
