package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Ld<T extends WordNumber> extends TargetSourceInstruction<T> {

  public Ld(State state, OpcodeReference<T> target, OpcodeReference<T> source) {
    super(state, target, source);
  }

  public int execute() {
    T value = source.read();
    target.write(value);
    return cyclesCost;
  }
}
