package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Sub extends TargetSourceOpcode {

  public Sub(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target, source);
  }

  public int execute() {

    final int value1 = target.read();
    final int value2 = source.read();

    int alu8BitSub = flag.ALU8BitSub(value2, value1);
    target.write(alu8BitSub);

    return getCyclesCost();
  }

  public String toString() {
    return "SUB " + target + ", " + source;
  }

  public Object clone() throws CloneNotSupportedException {
    Sub xor = new Sub(state, (OpcodeReference) target.clone(), (OpcodeReference) source.clone());
    completeClone(xor);
    return xor;
  }
}
