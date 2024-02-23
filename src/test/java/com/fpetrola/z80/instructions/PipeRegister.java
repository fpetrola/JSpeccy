package com.fpetrola.z80.instructions;

import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

public class PipeRegister<T extends WordNumber> implements Register<T> {
  public T value;
  public ImmutableOpcodeReference<T> readImmutableOpcodeReference;

  public void increment() {
  }

  public void decrement() {
  }

  public RegisterName getName() {
    return RegisterName.VIRTUAL;
  }

  public T read() {
    if (value != null)
      return value;
    else
      return readImmutableOpcodeReference.read();
  }

  public void write(T value) {
    this.value = value;
  }

  public int getLength() {
    return 0;
  }

  public Object clone() throws CloneNotSupportedException {
    return null;
  }

  public OpcodeReference r(ImmutableOpcodeReference<T> readImmutableOpcodeReference) {
    this.readImmutableOpcodeReference = readImmutableOpcodeReference;
    return this;
  }
}
