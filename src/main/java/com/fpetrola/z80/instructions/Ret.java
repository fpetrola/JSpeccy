package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Ret extends AbstractOpCode {

  private final Condition condition;

  public Ret(State state, Condition condition) {
    super(state);
    this.condition = condition;
  }

  public int execute() {
    if (condition.conditionMet()) {
      int address = sp.read();
      int lsb = memory.read(address) & 0xff;
      final int value = ((memory.read(address + 1) << 8) & 0xff00 | lsb);
      sp.increment(2);
      
      state.setNextPC(value);

//      pc.write(value);
      return 11;
    } else {
      return 5;
    }
  }

  public String toString() {
    String conditionStr = condition.toString();
    return "RET" + ((conditionStr.length() > 0) ? " " + conditionStr : "");
  }
}
