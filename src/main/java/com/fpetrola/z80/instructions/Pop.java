package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Pop extends AbstractOpCode {

  private final OpcodeReference target;

  public Pop(State state, OpcodeReference target) {
    super(state);
    this.target = target;
  }

  public int execute() {
    pc.increment(1);
    int address = sp.read() & 0xffff;
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
