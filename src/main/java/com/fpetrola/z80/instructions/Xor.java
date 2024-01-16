package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;

public class Xor extends TargetSourceInstruction {

  public Xor(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target, source);
  }

  public int execute() {
    final int value1 = target.read();
    final int value2 = source.read();

    int aLU8BitXor = flag.ALU8BitXor(value2, value1);

    target.write(aLU8BitXor);

    return cyclesCost;
  }
}
