package com.fpetrola.z80.opcodes.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.TargetInstruction;

public class Dec16 extends TargetInstruction {

  public Dec16(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    target.write(target.read() - 1 & 0xFFFF);

    return cyclesCost;
  }
}
