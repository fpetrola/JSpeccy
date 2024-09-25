package com.fpetrola.z80.instructions.base;

public class AddressAction {
  public int address;

  public AddressAction() {
  }

  public AddressAction(int address) {
    this.address = address;
  }

  boolean processBranch(boolean doBranch, Instruction instruction, boolean alwaysTrue, SymbolicExecutionAdapter symbolicExecutionAdapter) {
    return true;
  }

  public int getNext(int next, int pcValue) {
    return pcValue;
  }

}
