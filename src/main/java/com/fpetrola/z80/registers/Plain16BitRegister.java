package com.fpetrola.z80.registers;

import com.fpetrola.z80.opcodes.references.WordNumber;

public class Plain16BitRegister<T extends WordNumber> implements Register<T> {

  protected T data;
  private String name;

  public Plain16BitRegister(String name) {
    this.name = name;
  }

  public Plain16BitRegister(RegisterName name) {
    this.name = name.name();
  }

  public T read() {
    return data;
  }

  public void write(T value) {
      this.data = value;
  }

  public String toString() {
    return name;
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

  public String getName() {
    return name;
  }
}
