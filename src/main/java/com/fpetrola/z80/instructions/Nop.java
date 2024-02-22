package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Nop<T extends WordNumber> extends AbstractInstruction<T> {
  Nop() {
  }

  public int execute() {
    return 4;
  }
}
