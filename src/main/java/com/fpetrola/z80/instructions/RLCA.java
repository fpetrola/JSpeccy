package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.opcodes.models.OpcodeReference;

public class RLCA  extends TargetInstruction {

  public RLCA(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    target.write(flag.RLCA(target.read()));

    return cyclesCost;
  }
}
