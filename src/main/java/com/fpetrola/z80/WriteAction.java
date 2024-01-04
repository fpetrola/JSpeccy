package com.fpetrola.z80;

import com.fpetrola.z80.registers.Register;

public class WriteAction {

  public int address;
  public int newValue;
  public int oldValue;
  public Register register;

  public WriteAction(int address, int oldValue, int newValue) {
    this.address = address;
    this.oldValue = oldValue;
    this.newValue = newValue;
  }

  public WriteAction(Register register, int oldValue, int newValue) {
    this.register = register;
    this.oldValue = oldValue;
    this.newValue = newValue;
  }
}
