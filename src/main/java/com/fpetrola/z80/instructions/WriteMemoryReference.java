package com.fpetrola.z80.instructions;

public class WriteMemoryReference {

  private int address;
  private int value;

  public WriteMemoryReference(int address, int value) {
    this.address = address;
    this.value = value;
    System.out.println(value + " -> mem(" + address + ")");
  }

}
