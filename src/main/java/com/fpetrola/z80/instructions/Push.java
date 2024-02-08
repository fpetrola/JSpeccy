package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Push<T extends WordNumber> extends TargetInstruction<T> {

  public Push(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    T value = target.read();
    sp.decrement(2);
    T address = sp.read();
    memory.write(address, value.and(0xFF));
    memory.write(address.plus(1), (value.right(8)));

    return 5 + target.cyclesCost();
  }
}
