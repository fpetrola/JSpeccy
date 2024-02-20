package com.fpetrola.z80.instructions;

import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

class PipeRegisterPair<T extends WordNumber> implements OpcodeReference<T> {

  private final ImmutableOpcodeReference<T> o1;
  private final ImmutableOpcodeReference<T> o2;

  public PipeRegisterPair(ImmutableOpcodeReference<T> o1, ImmutableOpcodeReference<T> o2) {
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
