package com.fpetrola.z80.spy;

import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.registers.RegisterName;

public class OpcodeReferenceSpy implements OpcodeReference {
  private OpcodeReference opcodeReference;
  private InstructionSpy spy;

  public OpcodeReferenceSpy(OpcodeReference opcodeReference, InstructionSpy InstructionSpy) {
    this.opcodeReference = opcodeReference;
    this.spy = InstructionSpy;
  }

  public void write(int value) {
    throw new RuntimeException("not implemented");
//    spy.addWriteReference(opcodeReference, value, false);
//    opcodeReference.write(value);
  }

  public int read() {
    throw new RuntimeException("not implemented");

//    int value = opcodeReference.read();
//    spy.addReadReference(opcodeReference, value);
//    return value;
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
    return new OpcodeReferenceSpy((OpcodeReference) opcodeReference.clone(), spy);
  }
}