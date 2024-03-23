package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.OpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class MemoryAccessOpcodeReference<T extends WordNumber> implements OpcodeReference<T> {
  private final ImmutableOpcodeReference<T> c;
  private Memory<T> mem;

  public MemoryAccessOpcodeReference(ImmutableOpcodeReference<T> c, Memory mem) {
    this.c = c;
    this.mem = mem;
  }

  @Override
  public T read() {
    return mem.read(c.read());
  }

  @Override
  public int getLength() {
    return 0;
  }

  @Override
  public void write(T value) {
    this.mem.write(c.read(), value);
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return new MemoryAccessOpcodeReference<>(c, mem);
  }

  @Override
  public String toString() {
    return "[" + c + "]";
  }
}
