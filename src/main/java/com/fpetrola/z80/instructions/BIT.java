package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class BIT extends TargetOpCode {

  private final int n;
  private int valueDelta;

  public BIT(State state, OpcodeReference target, int n, int valueDelta) {
    super(state, target);
    this.n = n;
    this.valueDelta = valueDelta;
  }

  public int execute() {
    final int value = target.read();
    flag.testBit(value, n);

    return getCyclesCost();
  }

  public String toString() {
    return "BIT " + n + ", " + target;
  }

  public int getLength() {
    return super.getLength() + (valueDelta != 0 ? 1 : 0);
  }
  
  public Object clone() throws CloneNotSupportedException {
    BIT xor = new BIT(state, (OpcodeReference) target.clone(), n, valueDelta);
    completeClone(xor);
    return xor;
  }
}
