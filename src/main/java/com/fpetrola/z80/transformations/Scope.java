package com.fpetrola.z80.transformations;

public class Scope {
  public int start = Integer.MAX_VALUE;
  public int end;

  public void include(VirtualRegister virtualRegister) {
    int address = virtualRegister.getAddress();
    include(address);
  }

  public void include(int address) {
    start = Math.min(start, address);
    end = Math.max(end, address);
  }
}
