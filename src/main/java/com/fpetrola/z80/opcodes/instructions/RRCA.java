package com.fpetrola.z80.opcodes.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.TargetInstruction;

public class RRCA extends TargetInstruction {

  public RRCA(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {

    final int value = target.read();
    int rrca = flag.RRCA(value);
    target.write(rrca);

    return cyclesCost;
  }
}
