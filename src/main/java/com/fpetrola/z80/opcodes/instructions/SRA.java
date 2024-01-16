package com.fpetrola.z80.opcodes.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.TargetInstruction;

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
