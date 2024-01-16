package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;

public class RRA extends TargetInstruction {

  public RRA(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    final int value = target.read();
    target.write(flag.RRA(value));

    return cyclesCost;
  }
}
