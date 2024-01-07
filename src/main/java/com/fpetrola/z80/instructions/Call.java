package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Call extends AbstractOpCode {

  public final Condition condition;
  public final OpcodeReference target;

  public Call(State state, Condition condition, OpcodeReference target) {
    super(state);
    this.target = target;
    this.condition = condition;
  }

  @Override
  public int execute() {
    if (condition.conditionMet()) {
      pc.increment(1);
      sp.decrement(2);
      final int position = target.read();
      final int address = sp.read();
      final int value = pc.read();
      memory.write(address, value & 0xFF);
      memory.write(address + 1, (value >> 8));
      pc.write(position);
      return 4 + 3 + 4 + target.cyclesCost();
    } else {
      pc.increment(3);

      return 4 + target.cyclesCost();
    }
  }

  @Override
  public String toString() {
    final String conditionStr = condition.toString();
    return "CALL " + ((conditionStr.length() > 0) ? conditionStr + "," : "") + target;
  }
}
