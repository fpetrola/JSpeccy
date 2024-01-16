package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.opcodes.models.Condition;
import com.fpetrola.z80.opcodes.models.OpcodeReference;

public class JP extends ConditionalInstruction {

  public JP(State state, OpcodeReference target, Condition condition) {
    super(state, target, condition);
  }

  public int execute() {
    final int position = target.read();

    if (condition.conditionMet()) {
      state.setNextPC(position);
      memptr.write(position);
    }

    return cyclesCost;
  }
}
