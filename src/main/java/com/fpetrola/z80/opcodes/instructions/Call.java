package com.fpetrola.z80.opcodes.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.opcodes.references.Condition;
import com.fpetrola.z80.opcodes.references.ConditionalInstruction;
import com.fpetrola.z80.opcodes.references.OpcodeReference;

public class Call extends ConditionalInstruction {

  public Call(State state, OpcodeReference target, Condition condition) {
    super(state, target, condition);
  }

  public int execute() {
    if (condition.conditionMet()) {
      sp.decrement(2);
      final int position = target.read();
      final int address = sp.read();
      final int value = pc.read() + length;
      memory.write(address, value & 0xFF);
      memory.write(address + 1, (value >> 8));
      state.setNextPC(position);
//      pc.write(position);
    }

    return cyclesCost;
  }
}
