package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class DAA<T extends WordNumber> extends TargetInstruction<T> {

  public DAA(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    final T a = target.read();
//        final int result = ~a;

//        Flags.copyFrom(flag, Flags.Y_FLAG | Flags.X_FLAG, result);
//        Flags.setFlag(flag, Flags.HALF_CARRY_FLAG, true);
//        Flags.setFlag(flag, Flags.NEGATIVE_FLAG, true);

    target.write(flag.DAA(a));

    return cyclesCost;
  }

}
