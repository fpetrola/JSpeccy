package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class Xor extends TargetSourceOpcode {

  public Xor(State state, OpcodeReference target, OpcodeReference source) {
    super(state, target, source);
  }

  public int execute() {
    final int value1 = target.read();
    final int value2 = source.read();

    int aLU8BitXor = flag.ALU8BitXor(value2, value1);

    target.write(aLU8BitXor);

    return getCyclesCost();
  }

  public String toString() {
    return "XOR " + source + " - " + target;
  }
  
  public Object clone() throws CloneNotSupportedException {
    Xor xor = new Xor(state, (OpcodeReference) target.clone(), (OpcodeReference) source.clone());
    completeClone(xor);
    return xor;
  }

}
