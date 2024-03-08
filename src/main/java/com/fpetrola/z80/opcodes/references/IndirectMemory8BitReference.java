package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.mmu.Memory;

public final class IndirectMemory8BitReference<T> implements OpcodeReference<T> {

  public ImmutableOpcodeReference<T> target;
  private final Memory<T> memory;

  public IndirectMemory8BitReference(ImmutableOpcodeReference target, Memory memory) {
    this.target = target;
    this.memory = memory;
  }

  public T read() {
    T address = target.read();
    final T value = memory.read(address);
    return value;
  }

  public void write(T value) {
    T address = target.read();
    memory.write(address, value);
  }

  public String toString() {
    return "(" + target + ")";
  }

  public int getLength() {
    return target.getLength();
  }

  public Object clone() throws CloneNotSupportedException {
    return new IndirectMemory8BitReference((ImmutableOpcodeReference) target.clone(), memory);
  }
}
