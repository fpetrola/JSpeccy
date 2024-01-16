package com.fpetrola.z80.opcodes.instructions;

import com.fpetrola.z80.State;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.TargetSourceInstruction;

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
