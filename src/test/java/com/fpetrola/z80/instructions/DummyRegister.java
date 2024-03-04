package com.fpetrola.z80.instructions;

import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

public class DummyRegister implements Register {
  @Override
  public void increment() {

  }

  @Override
  public void decrement() {

  }

  @Override
  public RegisterName getName() {
    return null;
  }

  @Override
  public Object read() {
    return null;
  }

  @Override
  public int getLength() {
    return 0;
  }

  @Override
  public void write(Object value) {

  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return null;
  }
}
