package com.fpetrola.z80.bytecode.se;

import com.fpetrola.z80.instructions.base.Instruction;

public class AddressAction {
  @Override
  public String toString() {
    return "AddressAction{" +
        "address=" + address +
        ", pending=" + pending +
        '}';
  }

  public int address;
  protected boolean pending;

  public AddressAction() {
  }

  public AddressAction(int address) {
    this.address = address;
  }

  public AddressAction(int address, boolean pending) {
    this.address = address;
    this.pending = pending;
  }

  public boolean processBranch(boolean doBranch, Instruction instruction, boolean alwaysTrue, SymbolicExecutionAdapter symbolicExecutionAdapter) {
    if (pending)
      pending = false;
    return true;
  }

  public int getNext(int next, int pcValue) {
    return pcValue;
  }

  public boolean isPending() {
    return pending;
  }

  public void setPending(boolean pending) {
    this.pending = pending;
  }
}
