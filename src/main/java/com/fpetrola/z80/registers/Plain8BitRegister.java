package com.fpetrola.z80.registers;

import com.fpetrola.z80.opcodes.references.WordNumber;

public class Plain8BitRegister<T extends WordNumber> implements Register<T> {

  protected T data;
  private final RegisterName name;

  public Plain8BitRegister(RegisterName name) {
    this.name = name;
  }

  public T read() {
    return data;
  }

  public void write(T value) {
    this.data = value.and(0xFF);
  }

  public int cyclesCost() {
    return 0;
  }

  public String toString() {
    return name.name();
  }

  public void increment(int by) {
    this.data = data.plus(by);
  }

  public void decrement(int by) {
    this.data = (data.minus(by)).and(0xFF);
  }

  public int getLength() {
    return 0;
  }

  public Plain8BitRegister clone() throws CloneNotSupportedException {
    return this;
  }

  public RegisterName getName() {
    return name;
  }
}
