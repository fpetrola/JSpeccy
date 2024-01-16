package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;

public class DAA extends TargetInstruction {

  public DAA(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    final int a = target.read();
//        final int result = ~a;

//        Flags.copyFrom(flag, Flags.Y_FLAG | Flags.X_FLAG, result);
//        Flags.setFlag(flag, Flags.HALF_CARRY_FLAG, true);
//        Flags.setFlag(flag, Flags.NEGATIVE_FLAG, true);

    target.write(flag.DAA(a));

    return cyclesCost;
  }

}
