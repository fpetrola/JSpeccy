package com.fpetrola.z80.opcodes.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.opcodes.references.AbstractInstruction;

public class Ldi extends AbstractInstruction {

  public Ldi(State state) {
    super(state);
  }

  public int execute() {
    int hlValue = hl.read();
    int deValue = de.read();
    int aValue = a.read();

    int work8 = memory.read(hlValue);
    memory.write(deValue, work8);

    hl.increment(1);
    de.increment(1);
    bc.decrement(1);

    flag.LDI(aValue, work8, bc.read());

    return 1;
  }
}
