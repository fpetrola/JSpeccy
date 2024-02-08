package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.Condition;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Ret<T extends WordNumber> extends AbstractInstruction<T> {
  private final Condition condition;

  public Ret(State state, Condition condition) {
    super(state);
    this.condition = condition;
  }

  public int execute() {
    setNextPC(condition.conditionMet() ? Pop.doPop(memory, sp) : null);
    return 11;
  }

  public String toString() {
    String conditionStr = condition.toString();
    return "RET" + ((conditionStr.length() > 0) ? " " + conditionStr : "");
  }

  public Condition getCondition() {
    return condition;
  }
}
