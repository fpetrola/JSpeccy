package com.fpetrola.z80.instructions;

public class ReadMemoryReference {
  private int address;
  private int value;

  public ReadMemoryReference(int address, int value) {
    this.address = address;
    this.value = value;

    System.out.println("mem(" + address + ") -> " + value);
  }

}
