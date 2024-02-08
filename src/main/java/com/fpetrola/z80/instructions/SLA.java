package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class SLA<T extends WordNumber> extends TargetInstruction<T> {
  public SLA(State state, OpcodeReference<T> target) {
    super(state, target);
  }

  public int execute() {

    final T value = target.read();
    T shiftGenericSLA = flag.shiftGenericSLA(value);
    target.write(shiftGenericSLA);

    return cyclesCost;
  }
}
