package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

public class JP extends AbstractOpCode {

  public final Condition condition;
  private final OpcodeReference target;
  private Register memptr;

  public JP(State state, Condition condition, OpcodeReference target) {
    super(state);
    this.target = target;
    this.condition = condition;
    memptr = state.getRegister(RegisterName.MEMPTR);
  }

  @Override
  public int execute() {
    pc.increment(1);

    final int position = target.read();

    if (condition.conditionMet()) {
      pc.write(position);
      memptr.write(position);
    }

    return 4 + target.cyclesCost();
  }

  @Override
  public String toString() {
    return "JP " + target;
  }
}
