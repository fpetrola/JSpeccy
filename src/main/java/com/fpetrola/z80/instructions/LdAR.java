package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class LdAR<T extends WordNumber> extends Ld<T> {

  public LdAR(State state, OpcodeReference<T> target, OpcodeReference<T> source) {
    super(state, target, source);
  }

  public int execute() {
    T value = source.read();
    T ldar = flag.LDAR(target.read(), value, state.isIff2());
   
    target.write(ldar);

    return cyclesCost;
  }

}
