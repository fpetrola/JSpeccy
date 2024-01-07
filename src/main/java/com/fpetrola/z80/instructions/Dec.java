package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Dec extends TargetOpCode {

  public Dec(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {

    pc.increment(1);

    final int value = target.read();

    int alu8BitDec = flag.ALU8BitDec(value);

    target.write(alu8BitDec);

    return 4 + target.cyclesCost() + target.cyclesCost();
  }

  @Override
  public String toString() {
    return "DEC " + target;
  }

}
