package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.opcodes.models.OpcodeReference;

public class Sbc extends TargetSourceInstruction {

  public Sbc(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target, source);
  }

  public int execute() {

    final int value1 = target.read();
    final int value2 = source.read();
    int result = flag.ALU8BitSbc(value2, value1);
    target.write(result);

    return cyclesCost;
  }
}
