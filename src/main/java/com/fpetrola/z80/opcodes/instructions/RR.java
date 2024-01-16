package com.fpetrola.z80.opcodes.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.TargetInstruction;

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
