package com.fpetrola.z80.spy;

import com.fpetrola.z80.opcodes.references.OpcodeReference;

public class OpcodeReferenceSpy implements OpcodeReference {
  private OpcodeReference opcodeReference;
  private OpcodesSpy spy;

  public OpcodeReferenceSpy(OpcodeReference opcodeReference, OpcodesSpy opcodesSpy) {
    this.opcodeReference = opcodeReference;
    this.spy = opcodesSpy;
  }

  public void write(int value) {
    spy.addWriteReference(opcodeReference, value, false);
    opcodeReference.write(value);
  }

  public int read() {
    int value = opcodeReference.read();
    spy.addReadReference(opcodeReference, value);
    return value;
  }

  public int cyclesCost() {
    return opcodeReference.cyclesCost();
  }

  public String toString() {
    return opcodeReference.toString();
  }

  public int getLength() {
    return opcodeReference.getLength();
  }

  public Object clone() throws CloneNotSupportedException {
    return opcodeReference.clone();
  }
}