package com.fpetrola.z80.opcodes.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.TargetInstruction;

public class RLCA  extends TargetInstruction {

  public RLCA(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    target.write(flag.RLCA(target.read()));

    return cyclesCost;
  }
}
