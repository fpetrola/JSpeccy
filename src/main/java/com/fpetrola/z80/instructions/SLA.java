package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;

public class SLA extends TargetInstruction {

  public SLA(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {

    final int value = target.read();
    int shiftGenericSLA = flag.shiftGenericSLA(value);
    target.write(shiftGenericSLA);

    return cyclesCost;
  }
}
