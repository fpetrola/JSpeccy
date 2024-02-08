package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class RRCA<T extends WordNumber> extends TargetInstruction<T> {

  public RRCA(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {

    final T value = target.read();
    T rrca = flag.RRCA(value);
    target.write(rrca);

    return cyclesCost;
  }
}
