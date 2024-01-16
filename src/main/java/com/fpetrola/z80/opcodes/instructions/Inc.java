package com.fpetrola.z80.opcodes.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.TargetInstruction;

public class Inc extends TargetInstruction {

  public Inc(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    int alu8BitInc = flag.ALU8BitInc(target.read());

    target.write(alu8BitInc);

    return cyclesCost;
  }
}
