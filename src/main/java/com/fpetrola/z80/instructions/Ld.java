package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.BaseImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Ld<T extends WordNumber> extends TargetSourceInstruction<T> {

  public Ld(State state, OpcodeReference<T> target, ImmutableOpcodeReference<T> source) {
    super(state, target, source);
  }

  public int execute() {
    T value = source.read();

    T aLU8Assign = flag.ALU8Assign(value);

    target.write(aLU8Assign);
    return cyclesCost;
  }
}
