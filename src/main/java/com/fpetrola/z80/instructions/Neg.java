package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.opcodes.models.OpcodeReference;

public class Neg extends TargetInstruction {
  public Neg(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    int neg = flag.NEG(target.read());
    target.write(neg);

    return cyclesCost;
  }

}
