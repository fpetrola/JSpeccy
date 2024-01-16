package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.opcodes.models.OpcodeReference;

public class Out extends TargetSourceInstruction {

  public Out(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target, source);
  }

  public int execute() {
    int port = target.read();
    int value = source.read();
    state.getIo().out(port, value);

    return cyclesCost;
  }
}
