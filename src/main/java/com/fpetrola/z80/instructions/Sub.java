package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Sub<T extends WordNumber> extends TargetSourceInstruction<T> {

  public Sub(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target, source);
  }

  public int execute() {
    final T value1 = target.read();
    final T value2 = source.read();

    T alu8BitSub = flag.ALU8BitSub(value2, value1);
    target.write(alu8BitSub);

    return cyclesCost;
  }
}
