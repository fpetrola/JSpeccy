package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.Condition;

public class Ret extends AbstractInstruction {

  private final Condition condition;

  public Ret(State state, Condition condition) {
    super(state);
    this.condition = condition;
  }

  public int execute() {
    if (condition.conditionMet()) {
      int address = sp.read();
      final int value = ((memory.read(address + 1) << 8) & 0xff00 | memory.read(address) & 0xff);
      sp.increment(2);
      setNextPC(value);
      return 11;
    } else {
      setNextPC(-1);
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
