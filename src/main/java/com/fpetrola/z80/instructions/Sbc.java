package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.BaseImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Sbc<T extends WordNumber> extends TargetSourceInstruction<T> {

  public Sbc(State state, OpcodeReference target, ImmutableOpcodeReference source) {
    super(state, target, source);
  }

  public int execute() {

    final T value1 = target.read();
    final T value2 = source.read();
    T result = flag.ALU8BitSbc(value2, value1);
    target.write(result);

    return cyclesCost;
  }
}
