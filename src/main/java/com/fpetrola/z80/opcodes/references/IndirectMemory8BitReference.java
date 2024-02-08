package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.spy.InstructionSpy;

public final class IndirectMemory8BitReference<T> implements OpcodeReference<T> {

  public final OpcodeReference<T> target;
  private final Memory<T> memory;
  private InstructionSpy spy;

  public IndirectMemory8BitReference(OpcodeReference target, Memory memory, InstructionSpy spy) {
    this.target = target;
    this.memory = memory;
    this.spy = spy;
  }

  public T read() {
    spy.switchToIndirectReference();
    T address = target.read();
    spy.switchToDirectReference();
    final T value = memory.read(address);
    return value;
  }

  public void write(T value) {
    spy.switchToIndirectReference();
    T address = target.read();
    spy.switchToDirectReference();
    memory.write(address, value);
  }

  public int cyclesCost() {
    return 3 + target.cyclesCost();
  }

  public String toString() {
    return "(" + target + ")";
  }

  public int getLength() {
    return target.getLength();
  }

  public Object clone() throws CloneNotSupportedException {
    return new IndirectMemory8BitReference((OpcodeReference) target.clone(), memory, spy);
  }
}
