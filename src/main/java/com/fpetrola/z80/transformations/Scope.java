package com.fpetrola.z80.transformations;

import com.fpetrola.z80.opcodes.references.WordNumber;

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

  public <T extends WordNumber> boolean isIncluding(VirtualRegister<T> register) {
    int registerLine = register.getRegisterLine();
    return registerLine >= start && registerLine <= end;
  }

  public int size() {
    return end - start;
  }
}
