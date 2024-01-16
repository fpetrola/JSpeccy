package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.opcodes.models.OpcodeReference;

public class RLA extends TargetInstruction {

  public RLA(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {

    final int value = target.read();
    target.write(flag.RLA(value));

    return cyclesCost;
  }
}
