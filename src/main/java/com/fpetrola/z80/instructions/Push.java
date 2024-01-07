package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Push extends AbstractOpCode {

  private final OpcodeReference target;

  public Push(State state, OpcodeReference target) {
    super(state);
    this.target = target;
  }

  @Override
  public int execute() {
    pc.increment(1);
    int value = target.read();
    sp.decrement(2);
    int address = sp.read();
    memory.write(address, value & 0xFF);
    memory.write(address + 1, (value >> 8));

    return 5 + target.cyclesCost();
  }

  @Override
  public String toString() {
    return "PUSH " + target;
  }
}
