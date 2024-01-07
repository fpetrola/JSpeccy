package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Ex extends TargetSourceOpcode {

  public Ex(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target, source);
  }

  public int execute() {
    final int v1 = target.read();
    final int v2 = source.read();

    if (target == af) { // FIXIT with wrappers equals
      flag.EXAFAF(af, _af);
    } else {
      target.write(v2);
      source.write(v1);
    }
    return 4 + target.cyclesCost() + source.cyclesCost();
  }

  public String toString() {
    return "EX " + target + ", " + source;
  }

}
