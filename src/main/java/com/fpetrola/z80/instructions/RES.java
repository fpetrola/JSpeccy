package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class RES extends TargetOpCode {

  private final int n;
  private int valueDelta;

  public RES(State state, OpcodeReference target, int n, int valueDelta) {
    super(state, target);
    this.n = n;
    this.valueDelta = valueDelta;
  }

  public int execute() {
    final int value = target.read();
    final int bit = 1 << n;
    final int result = value & ~bit;
    target.write(result);

    return 4 + target.cyclesCost() + target.cyclesCost();
  }

  public String toString() {
    return "RES " + n + ", " + target;
  }

  public int getLength() {
    return super.getLength() + (valueDelta != 0 ? 1 : 0);
  }
  
  
  public Object clone() throws CloneNotSupportedException {
    RES xor = new RES(state, (OpcodeReference) target.clone(), n, valueDelta);
    completeClone(xor);
    return xor;
  }
}
