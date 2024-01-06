package com.fpetrola.z80.instructions;

public class ReadOpcodeReference {

  public OpcodeReference opcodeReference;
  public int value;

  public ReadOpcodeReference(OpcodeReference opcodeReference, int value) {
    this.opcodeReference = opcodeReference;
    this.value = value;
  }

  public String toString() {
    return value + "= " + this.opcodeReference.toString();
  }

}
