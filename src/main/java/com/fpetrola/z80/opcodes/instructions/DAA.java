package com.fpetrola.z80.opcodes.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.TargetInstruction;

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
