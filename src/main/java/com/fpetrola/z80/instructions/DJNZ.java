package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class DJNZ extends TargetOpCode {

  public DJNZ(State state, OpcodeReference source) {
    super(state, source);
  }

  public int execute() {
    b.decrement(1);

    int counter = b.read();
    byte by = (byte) target.read();

    if (counter != 0) {
      int position = pc.read() + by + 1;
      state.setNextPC(position);
    }
    return cyclesCost;
  }

  @Override
  public String toString() {
    return "DJNZ " + target.toString();
  }
}
