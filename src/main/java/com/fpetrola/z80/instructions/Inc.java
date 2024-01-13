package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Inc extends TargetOpCode {

  public Inc(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    int lastPC = pc.read();

    //    timer.start();
    int alu8BitInc = flag.ALU8BitInc(target.read());
//    long average = timer.end();
    
    if (target instanceof MemoryPlusRegister8BitReference)
      pc.write(lastPC);
    
    target.write(alu8BitInc);

    return 4 + target.cyclesCost() + target.cyclesCost();
  }

  public String toString() {
    return "INC " + target;
  }
}
