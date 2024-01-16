package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.opcodes.models.OpcodeReference;

public class LdAR extends Ld {

  public LdAR(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target, source);
  }

  public int execute() {
    int value = source.read();
    int ldar = flag.LDAR(target.read(), value, state.isIff2());
   
    target.write(ldar);

    return cyclesCost;
  }

}
