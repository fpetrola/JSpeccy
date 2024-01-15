package com.fpetrola.z80.instructions;

import com.fpetrola.z80.State;

public abstract class BitOperation extends TargetOpCode {

  protected final int n;
  protected int valueDelta;

  public BitOperation(State state, OpcodeReference target, int n, int valueDelta) {
    super(state, target);
    this.n = n;
    this.valueDelta = valueDelta;
  }
  
  public int getLength() {
    return super.getLength() + (valueDelta != 0 ? 1 : 0);
  }
}