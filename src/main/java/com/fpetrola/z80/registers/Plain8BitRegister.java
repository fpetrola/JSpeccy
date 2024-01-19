package com.fpetrola.z80.registers;

public class Plain8BitRegister implements Register {

  protected int data;
  private final RegisterName name;

  public Plain8BitRegister(RegisterName name) {
    this.name = name;
  }

  public int read() {
    return data;
  }

  public void write(int value) {
    this.data = value & 0xFF;
  }

  public int cyclesCost() {
    return 0;
  }

  public String toString() {
    return name.name();
  }

  public void increment(int by) {
    this.data += by;
  }

  public void decrement(int by) {
    this.data = (data - by) & 0xFF;
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
