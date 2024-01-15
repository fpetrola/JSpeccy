package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public class SET extends TargetOpCode {

  private final int n;
  private int valueDelta;

  public SET(State state, OpcodeReference target, int n, int valueDelta) {
    super(state, target);
    this.n = n;
    this.valueDelta = valueDelta;
  }

  @Override
  public int execute() {
    final int value = target.read();
    final int bit = 1 << n;
    final int result = value | bit;
    target.write(result);

    return getCyclesCost();
  }

  public String toString() {
    return "SET " + n + ", " + target;
  }

  public int getLength() {
    return super.getLength() + (valueDelta != 0 ? 1 : 0);
  }

  public Object clone() throws CloneNotSupportedException {
    SET xor = new SET(state, (OpcodeReference) target.clone(), n, valueDelta);
    completeClone(xor);
    return xor;
  }
}
