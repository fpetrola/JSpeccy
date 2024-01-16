package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.opcodes.models.OpcodeReference;

public class RLC extends TargetInstruction {

  public RLC(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    final int value = target.read();
    int shiftGenericRLC = flag.shiftGenericRLC(value);
    target.write(shiftGenericRLC);

    return cyclesCost;
  }
}
