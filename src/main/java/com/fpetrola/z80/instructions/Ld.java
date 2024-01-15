package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Ld extends TargetSourceOpcode {

  public Ld(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target, source);
  }

  public int execute() {
    int value = source.read();
    target.write(value);

    return 4 + target.cyclesCost() + source.cyclesCost();
  }

  public String toString() {
    return "LD " + target + "," + source;
  }

  public Object clone() throws CloneNotSupportedException {
    Ld ld = new Ld(state, (OpcodeReference) target.clone(), (OpcodeReference) source.clone());
    completeClone(ld);
    return ld;
  }
}
