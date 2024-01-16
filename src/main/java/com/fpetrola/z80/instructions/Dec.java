package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.opcodes.models.OpcodeReference;

public class Dec extends TargetInstruction {

  public Dec(State state, OpcodeReference target) {
    super(state, target);
  }

  public int execute() {
    final int value = target.read();

    int alu8BitDec = flag.ALU8BitDec(value);

    target.write(alu8BitDec);

    return cyclesCost;
  }
}
