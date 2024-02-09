package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class RLD<T extends WordNumber> extends AbstractInstruction<T> {

  public RLD(State state) {
    super(state);
  }

  public int execute() {
    int reg_A = a.read().intValue();
    r.increment(1);

    int temp = memory.read(hl.read()).intValue();
    int nibble1 = (reg_A & 0x00F0) >> 4;
    int nibble2 = reg_A & 0x000F;
    int nibble3 = (temp & 0x00F0) >> 4;
    int nibble4 = temp & 0x000F;
    reg_A = (nibble1 << 4) | nibble3;
    temp = (nibble4 << 4) | nibble2;

    memory.write(hl.read(), WordNumber.createValue(temp));
    flag.RLD(WordNumber.createValue(reg_A));
    
    a.write(WordNumber.createValue(reg_A));

    return 1;
  }
}
