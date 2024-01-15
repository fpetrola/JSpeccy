package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class LdAR extends Ld {

  public LdAR(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target, source);
  }

  public int execute() {
    int value = source.read();
    int ldar = flag.LDAR(target.read(), value, state.isIff2());
   
    target.write(ldar);

    return getCyclesCost();
  }

}
