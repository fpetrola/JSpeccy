package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.opcodes.models.OpcodeReference;

public class SRL extends TargetInstruction {

  public SRL(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {

    final int value = target.read();
    int shiftGenericSRL = flag.shiftGenericSRL(value);
    target.write(shiftGenericSRL);

    return cyclesCost;
  }
}
