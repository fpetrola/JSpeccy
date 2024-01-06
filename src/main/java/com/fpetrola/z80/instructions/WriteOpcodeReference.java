package com.fpetrola.z80.instructions;

public class WriteOpcodeReference {

  public OpcodeReference opcodeReference;
  public int value;

  public WriteOpcodeReference(OpcodeReference opcodeReference, int value) {
    this.opcodeReference = opcodeReference;
    this.value = value;
  }

  public String toString() {
    return this.opcodeReference.toString() + ":= " + this.value;
  }

}
