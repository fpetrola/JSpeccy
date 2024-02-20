package com.fpetrola.z80.instructions;

import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;
import com.fpetrola.z80.registers.RegisterPair;

class PipeRegisterPair<T extends WordNumber> implements OpcodeReference<T> {

  private final OpcodeReference<T> o1;
  private final OpcodeReference<T> o2;

  public PipeRegisterPair(OpcodeReference<T> o1, OpcodeReference<T> o2) {
    this.o1 = o1;
    this.o2 = o2;
  }

  @Override
  public T read() {
    return null;
  }

  @Override
  public void write(T value) {

  }

  @Override
  public int getLength() {
    return 0;
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return null;
  }
}
