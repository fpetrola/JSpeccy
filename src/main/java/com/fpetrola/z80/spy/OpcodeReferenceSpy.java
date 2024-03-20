package com.fpetrola.z80.spy;

import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;

public class OpcodeReferenceSpy<T> implements OpcodeReference<T> {
  private ImmutableOpcodeReference immutableOpcodeReference;
  private InstructionSpy spy;

  public OpcodeReferenceSpy(ImmutableOpcodeReference immutableOpcodeReference, InstructionSpy InstructionSpy) {
    this.immutableOpcodeReference = immutableOpcodeReference;
    this.spy = InstructionSpy;
  }

  public void write(T value) {
    throw new RuntimeException("not implemented");
//    spy.addWriteReference(opcodeReference, value, false);
//    opcodeReference.write(value);
  }

  public T read() {
    throw new RuntimeException("not implemented");

//    int value = opcodeReference.read();
//    spy.addReadReference(opcodeReference, value);
//    return value;
  }

  public String toString() {
    return immutableOpcodeReference.toString();
  }

  public int getLength() {
    return immutableOpcodeReference.getLength();
  }

  public Object clone() throws CloneNotSupportedException {
    return new OpcodeReferenceSpy((ImmutableOpcodeReference) immutableOpcodeReference.clone(), spy);
  }
}