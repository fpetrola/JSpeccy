package com.fpetrola.z80.opcodes.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.TargetInstruction;

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
