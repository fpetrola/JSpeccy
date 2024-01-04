package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.mmu.Memory;

public class Ret extends AbstractOpCode {

  private final Condition condition;
  private final Memory memory;

  public Ret(State state, Condition condition, Memory memory) {
    super(state);
    this.condition = condition;
    this.memory = memory;
  }

  public int execute() {
    if (condition.conditionMet()) {
      int address = sp.read();
      int lsb = memory.read(address, false) & 0xff;
      final int value = ((memory.read(address + 1, false) << 8) & 0xff00 | lsb);
      sp.increment(2);
      pc.write(value);
      return 11;
    } else {
      pc.increment(1);
      return 5;
    }
  }

  public String toString() {
    String conditionStr = condition.toString();
    return "RET" + ((conditionStr.length() > 0) ? " " + conditionStr : "");
  }
}
