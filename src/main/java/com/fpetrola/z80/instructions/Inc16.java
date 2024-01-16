package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.opcodes.models.OpcodeReference;

public class Inc16 extends TargetInstruction {

  public Inc16(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    target.write(target.read() + 1 & 0xFFFF);
    return cyclesCost;
  }
}
