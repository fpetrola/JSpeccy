package com.fpetrola.z80.opcodes.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.TargetInstruction;

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
