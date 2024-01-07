package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Inc extends TargetOpCode {

  public Inc(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    pc.increment(1);
    //    timer.start();
    int alu8BitInc = flag.ALU8BitInc(target.read());
//    long average = timer.end();
    target.write(alu8BitInc);

    return 4 + target.cyclesCost() + target.cyclesCost();
  }

  public String toString() {
    return "INC " + target;
  }
}
