package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Dec extends TargetOpCode {

  public Dec(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    final int value = target.read();

    int alu8BitDec = flag.ALU8BitDec(value);

    target.write(alu8BitDec);

    return cyclesCost;
  }

  @Override
  public String toString() {
    return "DEC " + target;
  }

}
