package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;

public class SLL extends TargetInstruction {

  public SLL(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    final int value = target.read();
    target.write(flag.shiftGenericSLL(value));

    return cyclesCost;
  }
}
