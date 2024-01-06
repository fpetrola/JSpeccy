package com.fpetrola.z80.registers;

import org.apache.commons.lang3.NotImplementedException;

public class Plain16BitRegister implements Register {

  protected int data;
  private String name;

  public Plain16BitRegister(String name) {
    this.name = name;
  }

  public int read() {
    return data & 0xFFFF;
  }

  public void write(int value) {
    this.data = value & 0xFFFF;
  }

  public int cyclesCost() {
    return 0;
  }

  public void writeToRealEmulator(int value) {
    throw new NotImplementedException("");
  }

  public String toString() {
    return name;
  }

  public int readFromRealEmulator() {
    throw new NotImplementedException("");
  }

  public void increment(int by) {
    data = (data + by) & 0xffff;
  }

  public void decrement(int by) {
    data = (data - by) & 0xffff;
  }
}
