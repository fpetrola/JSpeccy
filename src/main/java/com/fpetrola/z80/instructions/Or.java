package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Or extends TargetSourceOpcode {

  public Or(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target, source);
  }

  public int execute() {
    final int value1 = target.read();
    final int value2 = source.read();

    int alu8BitOr = flag.ALU8BitOr(value2, value1);

    target.write(alu8BitOr);

    return getCyclesCost();
  }

  public String toString() {
    return "OR " + target + ", " + source;
  }

  public Object clone() throws CloneNotSupportedException {
    Or xor = new Or(state, (OpcodeReference) target.clone(), (OpcodeReference) source.clone());
    completeClone(xor);
    return xor;
  }
}
