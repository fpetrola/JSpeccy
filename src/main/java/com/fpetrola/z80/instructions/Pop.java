package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Pop extends TargetOpCode {

  public Pop(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    int address = sp.read() & 0xffff;
    final int value = ((memory.read(address + 1) << 8) & 0xff00 | memory.read(address) & 0xff);
    sp.increment(2);
    target.write(value);

    return 5 + 3 + 3;
  }
}
