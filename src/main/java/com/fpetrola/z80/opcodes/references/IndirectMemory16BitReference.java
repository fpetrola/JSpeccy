package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.spy.InstructionSpy;

public final class IndirectMemory16BitReference<T extends WordNumber> implements OpcodeReference<T> {
  public final ImmutableOpcodeReference<T> target;
  private final Memory<T> memory;

  public IndirectMemory16BitReference(ImmutableOpcodeReference target, Memory memory) {
    this.target = target;
    this.memory = memory;
  }

  public T read() {
    T address = target.read();
    T fetchAddress = Memory.read16Bits(memory, address);
    return fetchAddress;
  }

  public void write(T value) {
    T address = target.read();

    Memory.write16Bits(memory, value, address);
  }

  public String toString() {
    return "(" + target.toString() + ")";
  }

  public int getLength() {
    return target.getLength();
  }

  public Object clone() throws CloneNotSupportedException {
    return new IndirectMemory16BitReference((ImmutableOpcodeReference) target.clone(), memory);
  }
}
