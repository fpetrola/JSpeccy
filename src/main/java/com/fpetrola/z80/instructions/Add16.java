package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;

public class Add16 extends TargetSourceInstruction {

  public Add16(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target, source);
  }

  public int execute() {
    final int value1 = source.read();
    final int value2 = target.read();
    target.write(flag.ALU16BitAdd(value2, value1));

    return cyclesCost;
  }
}
