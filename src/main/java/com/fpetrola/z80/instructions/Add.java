package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Add<T extends WordNumber> extends TargetSourceInstruction<T> {

  public Add(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target, source);
  }

  public int execute() {
    final T value1 = source.read();
    final T value2 = target.read();
    T ALU8BitAdd = flag.ALU8BitAdd(value1, value2);
    target.write(ALU8BitAdd);
    return cyclesCost;
  }
}
