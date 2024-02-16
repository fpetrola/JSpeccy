package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class DAA<T extends WordNumber> extends TargetInstruction<T> {

  public DAA(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    final T a = target.read();
    target.write(flag.DAA(a));

    return cyclesCost;
  }

}
