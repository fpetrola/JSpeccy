package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Out extends TargetSourceOpcode {

  public Out(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target, source);
  }

  public int execute() {
    int port = target.read();
    int value = source.read();
    state.getIo().out(port, value);

    return getCyclesCost();
  }

  public String toString() {
    return "OUT " + target + "," + source;
  }
}
