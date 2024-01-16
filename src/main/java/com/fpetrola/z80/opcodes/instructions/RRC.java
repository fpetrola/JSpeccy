package com.fpetrola.z80.opcodes.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.TargetInstruction;

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
