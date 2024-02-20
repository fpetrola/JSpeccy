package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.BaseImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Ex<T extends WordNumber> extends TargetSourceInstruction<T> {

  public Ex(State state, OpcodeReference target, ImmutableOpcodeReference source) {
    super(state, target, source);
  }

  public int execute() {
    final T v1 = target.read();
    final T v2 = source.read();

    target.write(v2);
    ((OpcodeReference) source).write(v1);
    return cyclesCost;
  }
}
