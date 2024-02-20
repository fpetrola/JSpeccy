package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.*;

public class JP<T extends WordNumber> extends ConditionalInstruction<T> {

  public JP(State state, ImmutableOpcodeReference target, Condition condition) {
    super(state, target, condition);
  }

  public int execute() {
    final T position = target.read();
    setJumpAddress(position);
    setNextPC(condition.conditionMet() ? position : null);
    return cyclesCost;
  }
}
