package com.fpetrola.z80.instructions;

import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class Immutable16BitsOpcodeReferencePair<T extends WordNumber> implements ImmutableOpcodeReference<T> {
  private final ImmutableOpcodeReference<T> high;
  private final ImmutableOpcodeReference<T> low;

  public Immutable16BitsOpcodeReferencePair(ImmutableOpcodeReference<T> high, ImmutableOpcodeReference<T> low) {
    this.high = high;
    this.low = low;
  }

  public T read() {
    return (high.read().left(8)).or(low.read());
  }

  public int getLength() {
    return 1;
  }

  public Object clone() throws CloneNotSupportedException {
    return null;
  }
}
