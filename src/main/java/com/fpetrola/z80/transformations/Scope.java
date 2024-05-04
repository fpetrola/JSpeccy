package com.fpetrola.z80.transformations;

public class Scope {
  public int start;
  public int end;

  public Scope() {
    this(0, Integer.MAX_VALUE);
  }

  public Scope(int start, int end) {
    this.start = start;
    this.end = end;
  }

  public void include(VirtualRegister virtualRegister) {
    int address = virtualRegister.getAddress();
    include(address);
  }

  private void include(int address) {
    start = Math.min(start, address);
    end = Math.max(end, address);
  }
}
