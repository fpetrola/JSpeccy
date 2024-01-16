package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.opcodes.models.OpcodeReference;

public class Push extends TargetInstruction {

  public Push(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    int value = target.read();
    sp.decrement(2);
    int address = sp.read();
    memory.write(address, value & 0xFF);
    memory.write(address + 1, (value >> 8));

    return 5 + target.cyclesCost();
  }
}
