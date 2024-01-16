package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.opcodes.models.OpcodeReference;

public class SRA extends TargetInstruction {

  public SRA(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {

    final int value = target.read();
    int shiftGenericSRA = flag.shiftGenericSRA(value);
    target.write(shiftGenericSRA);

    return cyclesCost;
  }
}
