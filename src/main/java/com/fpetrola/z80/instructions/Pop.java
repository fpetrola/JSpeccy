package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.mmu.Memory;

public class Pop extends AbstractOpCode {

  private final OpcodeReference target;
  private final Memory memory;

  public Pop(State state, OpcodeReference target, Memory memory) {
    super(state);
    this.target = target;
    this.memory = memory;
  }

  public int execute() {
    pc.increment(1);
    int address = sp.read();
    int lsb = memory.read(address) & 0xff;
    final int value1 = ((memory.read(address + 1) << 8) & 0xff00 | lsb);
    sp.increment(2);
    target.write(value1);

    return 5 + 3 + 3;
  }

  public String toString() {
    return "POP " + target;
  }
}
