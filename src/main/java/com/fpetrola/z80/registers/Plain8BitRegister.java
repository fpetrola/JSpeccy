package com.fpetrola.z80.registers;

import com.fpetrola.z80.instructions.OpCode;

public class Plain8BitRegister implements Register {

  protected int data;
  private final String name;

  public Plain8BitRegister(String name) {
    this.name = name;
  }

  @Override
  public int read() {
    return data;
  }

  @Override
  public void write(int value) {
    this.data = value & 0xFF;
  }

  @Override
  public int cyclesCost() {
    return 0;
  }

  @Override
  public String toString() {
    return name;
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
}
