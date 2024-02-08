package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class RL<T extends WordNumber> extends TargetInstruction<T> {

  public RL(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    final T value = target.read();
    target.write(flag.shiftGenericRL(value));
    return cyclesCost;
  }
}
