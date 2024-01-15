package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Push extends TargetOpCode {

  public Push(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    int value = target.read();
    sp.decrement(2);
    int address = sp.read();
    memory.write(address, value & 0xFF);
    memory.write(address + 1, (value >> 8));

    return 5 + target.cyclesCost();
  }

  public String toString() {
    return "PUSH " + target;
  }

  public Object clone() throws CloneNotSupportedException {
    Push xor = new Push(state, (OpcodeReference) target.clone());
    completeClone(xor);
    return xor;
  }
}
