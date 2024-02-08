package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Inc<T extends WordNumber> extends TargetInstruction<T> {

  public Inc(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    T alu8BitInc = flag.ALU8BitInc(target.read());

    target.write(alu8BitInc);

    return cyclesCost;
  }
}
