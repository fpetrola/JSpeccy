package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Call extends ConditionalOpcode {

  public Call(State state, OpcodeReference target, Condition condition) {
    super(state, target, condition);
  }

  @Override
  public int execute() {
    if (condition.conditionMet()) {
      sp.decrement(2);
      final int position = target.read();
      final int address = sp.read();
      final int value = pc.read() + 2;
      memory.write(address, value & 0xFF);
      memory.write(address + 1, (value >> 8));
      state.setNextPC(position);
//      pc.write(position);
    }

    return cyclesCost;
  }

  public String toString() {
    final String conditionStr = condition.toString();
    return "CALL " + ((conditionStr.length() > 0) ? conditionStr + "," : "") + target;
  }
}
