package com.fpetrola.z80.instructions;

import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

public class DummyRegister<T> implements Register<T> {
  protected T value;

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
  public T read() {
    return value;
  }

  @Override
  public int getLength() {
    return 0;
  }

  @Override
  public void write(T value) {
    this.value = value;
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return null;
  }
}
