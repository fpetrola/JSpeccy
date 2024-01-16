package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.opcodes.models.OpcodeReference;

public class Ld extends TargetSourceInstruction {

  public Ld(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target, source);
  }

  public int execute() {
    int value = source.read();
    target.write(value);

    return cyclesCost;
  }
}
