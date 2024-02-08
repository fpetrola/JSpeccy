package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Neg<T extends WordNumber> extends TargetInstruction<T> {
  public Neg(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    T neg = flag.NEG(target.read());
    target.write(neg);

    return cyclesCost;
  }

}
