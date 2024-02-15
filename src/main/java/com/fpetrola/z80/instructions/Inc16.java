package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Inc16<T extends WordNumber> extends TargetInstruction<T> {

  public Inc16(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    target.write(target.read().plus1());
    return cyclesCost;
  }
}
