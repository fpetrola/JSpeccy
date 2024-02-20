package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.*;

public class JR<T extends WordNumber> extends ConditionalInstruction<T> {

  public JR(State state, ImmutableOpcodeReference target, Condition condition) {
    super(state, target, condition);
  }

  public int execute() {
    jumpRelativeIfMatchCondition();
    return cyclesCost;
  }
}
