package com.fpetrola.z80.instructions;

public class WriteMemoryReference {

  public int address;
  public int value;

  public WriteMemoryReference(int address, int value) {
    this.address = address;
    this.value = value;
  }

  public String toString() {
    return "mem(" + this.address + "):= " + this.value;
  }

}
