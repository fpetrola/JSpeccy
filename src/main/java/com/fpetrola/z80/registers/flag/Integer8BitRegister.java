package com.fpetrola.z80.registers.flag;

import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

public class Integer8BitRegister implements Register<Integer> {
  protected Integer data;
  private final String name;

  public Integer8BitRegister(String name) {
    this.name = name;
  }

  public Integer read() {
    return data;
  }

  public void write(Integer value) {
    this.data = value & 0xFF;
  }

  public String toString() {
    return name;
  }

  public void increment() {
    this.data = data + 1;
  }

  public void decrement() {
    this.data = (data - 1) & 0xFF;
  }

  public int getLength() {
    return 0;
  }

  public Integer8BitRegister clone() throws CloneNotSupportedException {
    return this;
  }

  public String getName() {
    return name;
  }
}