package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.spy.InstructionSpy;

public final class IndirectMemory16BitReference<T extends WordNumber> implements OpcodeReference<T> {

  public final OpcodeReference<T> target;
  private final Memory<T> memory;
  private InstructionSpy spy;

  public IndirectMemory16BitReference(OpcodeReference target, Memory memory, InstructionSpy spy) {
    this.target = target;
    this.memory = memory;
    this.spy = spy;
  }

  public T read() {
    spy.switchToIndirectReference();
    T address = target.read();
    spy.switchToDirectReference();
    T fetchAddress = Helper.read16Bits(memory, address);
    return fetchAddress;
  }

  public void write(T value) {
    spy.switchToIndirectReference();
    T address = target.read();
    spy.switchToDirectReference();

    Helper.write16Bits(value, address, memory);
  }

  public int cyclesCost() {
    return 6 + target.cyclesCost();
  }

  public String toString() {
    return "(" + target.toString() + ")";
  }

  public int getLength() {
    return target.getLength();
  }

  public Object clone() throws CloneNotSupportedException {
    return new IndirectMemory16BitReference((OpcodeReference) target.clone(), memory, spy);
  }
}
