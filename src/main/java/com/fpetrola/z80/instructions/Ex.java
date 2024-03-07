package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.MutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Ex<T extends WordNumber> extends TargetSourceInstruction<T, OpcodeReference<T>> {

  Ex(OpcodeReference target, OpcodeReference source) {
    super(target, source);
  }

  public int execute() {
    final T v1 = target.read();
    final T v2 = source.read();

    target.write(v2);
    source.write(v1);
    return cyclesCost;
  }
}
