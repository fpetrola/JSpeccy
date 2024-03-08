package com.fpetrola.z80.instructions;

import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

public class PipeRegister<T extends WordNumber> implements Register<T> {
  public T writtenValue;
  public ImmutableOpcodeReference<T> readSupplier;

  public void increment() {
  }

  public void decrement() {
  }

  public String getName() {
    return RegisterName.VIRTUAL.name();
  }

  public T read() {
    if (writtenValue != null)
      return writtenValue;
    else
      return readSupplier.read();
  }

  public void write(T value) {
    this.writtenValue = value;
  }

  public int getLength() {
    return 0;
  }

  public Object clone() throws CloneNotSupportedException {
    return null;
  }

  public OpcodeReference r(ImmutableOpcodeReference<T> readImmutableOpcodeReference) {
    this.readSupplier = readImmutableOpcodeReference;
    return this;
  }

  public String toString() {
    return "target";
  }
}
