package com.fpetrola.z80.instructions;

public class ReadMemoryReference {
  public int address;
  public int value;

  public ReadMemoryReference(int address, int value) {
    this.address = address;
    this.value = value;
  }

  public String toString() {
    return value + "= mem(" + this.address + ")";
  }

}
