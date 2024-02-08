package com.fpetrola.z80.instructions;

import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.instructions.base.AbstractInstruction;
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
    if (condition.conditionMet()) {
      T address = sp.read();
      final T value = Helper.read16Bits(memory, address);
      sp.increment(2);
      setNextPC(value);
      return 11;
    } else {
      setNextPC(null);
      return 5;
    }
  }

  public String toString() {
    String conditionStr = condition.toString();
    return "RET" + ((conditionStr.length() > 0) ? " " + conditionStr : "");
  }

  public Condition getCondition() {
    return condition;
  }
}
