package com.fpetrola.z80.registers;

import com.fpetrola.z80.opcodes.references.WordNumber;

public class Plain16BitRegister<T extends WordNumber> implements Register<T> {

  protected T data;
  private RegisterName name;

  public Plain16BitRegister(RegisterName name) {
    this.name = name;
  }

  public T read() {
    return data;
  }

  public void write(T value) {
    if (this.data == null)
      this.data = value;
    else
      this.data.set(value);
  }

  public int cyclesCost() {
    return 0;
  }

  public String toString() {
    return name.name();
  }

  public void increment() {
    data = data.plus(1);
  }

  public void decrement() {
    data = data.minus1();
  }

  public int getLength() {
    return 0;
  }

  public Object clone() throws CloneNotSupportedException {
    return this;
  }

  public RegisterName getName() {
    return name;
  }
}
