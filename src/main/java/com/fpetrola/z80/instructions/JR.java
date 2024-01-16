package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.Condition;
import com.fpetrola.z80.opcodes.references.OpcodeReference;

public class JR extends ConditionalInstruction {

  public JR(State state, OpcodeReference target, Condition condition) {
    super(state, target, condition);
  }

  public int execute() {
    byte by = (byte) target.read();
    if (condition.conditionMet()) {
      int position = pc.read() + length + by;
      state.setNextPC(position);
    }

    return cyclesCost;
  }
}
