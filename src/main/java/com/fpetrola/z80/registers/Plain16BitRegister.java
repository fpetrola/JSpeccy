package com.fpetrola.z80.registers;

import com.fpetrola.z80.opcodes.references.WordNumber;

public class Plain16BitRegister<T extends WordNumber> implements Register<T> {

  protected T data;
  private RegisterName name;

  public Plain16BitRegister(RegisterName name) {
    this.name = name;
  }

  public T read() {
    return data.and(0xFFFF);
  }

  public void write(T value) {
    this.data = value.and(0xFFFF);
  }

  public int cyclesCost() {
    return 0;
  }

  public String toString() {
    return name.name();
  }

  public void increment(int by) {
    data = data.plus(by).and(0xffff);
  }

  public void decrement(int by) {
    data = data.minus(by).and(0xffff);
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
