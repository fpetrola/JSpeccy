package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.spy.InstructionSpy;

public final class IndirectMemory16BitReference<T extends WordNumber> implements OpcodeReference<T> {

  public final BaseImmutableOpcodeReference<T> target;
  private final Memory<T> memory;
  private InstructionSpy spy;

  public IndirectMemory16BitReference(BaseImmutableOpcodeReference target, Memory memory, InstructionSpy spy) {
    this.target = target;
    this.memory = memory;
    this.spy = spy;
  }

  public T read() {
    spy.switchToIndirectReference();
    T address = target.read();
    spy.switchToDirectReference();
    T fetchAddress = Memory.read16Bits(memory, address);
    return fetchAddress;
  }

  public void write(T value) {
    spy.switchToIndirectReference();
    T address = target.read();
    spy.switchToDirectReference();

    Memory.write16Bits(memory, value, address);
  }

  public String toString() {
    return "(" + target.toString() + ")";
  }

  public int getLength() {
    return target.getLength();
  }

  public Object clone() throws CloneNotSupportedException {
    return new IndirectMemory16BitReference((BaseImmutableOpcodeReference) target.clone(), memory, spy);
  }
}
