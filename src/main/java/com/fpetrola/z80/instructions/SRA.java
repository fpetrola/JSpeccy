package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InvertedFetchInstruction;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class SRA<T extends WordNumber> extends InvertedFetchInstruction<T> {

  public SRA(State state, OpcodeReference target, int valueDelta) {
    super(state, target, valueDelta);
  }

  public int execute() {

    final T value = target.read();
    T shiftGenericSRA = flag.shiftGenericSRA(value);
    target.write(shiftGenericSRA);

    return cyclesCost;
  }
}
