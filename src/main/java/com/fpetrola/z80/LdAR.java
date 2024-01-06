package com.fpetrola.z80;

import com.fpetrola.z80.instructions.Ld;
import com.fpetrola.z80.instructions.OpcodeReference;

public class LdAR extends Ld {

  public LdAR(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target, source);
  }

  public int execute() {
    pc.increment(1);
    int value = source.read();
    int ldar = flag.LDAR(target.read(), value, state.isIff2());
   
    target.write(ldar);

    return 4 + target.cyclesCost() + source.cyclesCost();
  }

}
