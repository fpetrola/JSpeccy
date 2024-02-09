package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class RLCA<T extends WordNumber>  extends TargetInstruction<T> {

  public RLCA(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    T read = target.read();
    T rlca = flag.RLCA(read);
    target.write(rlca);

    return cyclesCost;
  }
}
