package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.Condition;
import com.fpetrola.z80.opcodes.references.OpcodeReference;

public class Call extends ConditionalInstruction {

  public Call(State state, OpcodeReference target, Condition condition) {
    super(state, target, condition);
  }

  public int execute() {
    spy.pause();
    final int position = target.read();
    if (condition.conditionMet()) {
      sp.decrement(2);
      final int address = sp.read();
      final int value = pc.read() + length;
      memory.write(address, value & 0xFF);
      memory.write(address + 1, (value >> 8));
      setNextPC(position);
//      pc.write(position);
    }
    else
      setNextPC(-1);

    spy.doContinue();

    return cyclesCost;
  }
}
