package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;

public class RR extends TargetInstruction {

  public RR(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {

    final int value = target.read();
    int shiftGenericRR = flag.shiftGenericRR(value);
    target.write(shiftGenericRR);

    return cyclesCost;
  }
}
