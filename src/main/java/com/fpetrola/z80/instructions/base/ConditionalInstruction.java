package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.Condition;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public abstract class ConditionalInstruction<T extends WordNumber> extends TargetInstruction<T> {

  protected Condition condition;

  public ConditionalInstruction(State state, OpcodeReference<T> target, Condition condition) {
    super(state, target);
    this.condition = condition;
  }

  public String toString() {
    return spy.executeInPause(() -> getClass().getSimpleName() + " " + ((condition.toString().length() > 0) ? condition.toString() + ", " : "") + target);
  }

  public Condition getCondition() {
    return condition;
  }
}