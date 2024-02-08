package com.fpetrola.z80.instructions;

import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Pop<T extends WordNumber> extends TargetInstruction<T> {

  public Pop(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    T address = sp.read().and(0xffff);
    final T value = Helper.read16Bits(memory, address);
    sp.increment(2);
    target.write(value);

    return 5 + 3 + 3;
  }

}
