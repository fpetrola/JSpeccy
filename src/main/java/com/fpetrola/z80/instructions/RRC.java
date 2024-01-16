package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.opcodes.models.OpcodeReference;

public class RRC extends TargetInstruction {

  public RRC(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {

    final int value = target.read();
    int shiftGenericRRC = flag.shiftGenericRRC(value);
    target.write(shiftGenericRRC);

    return cyclesCost;
  }
}
