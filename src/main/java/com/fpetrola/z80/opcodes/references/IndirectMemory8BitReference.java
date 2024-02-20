package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.spy.InstructionSpy;

public final class IndirectMemory8BitReference<T> implements OpcodeReference<T> {

  public final BaseImmutableOpcodeReference<T> target;
  private final Memory<T> memory;
  private InstructionSpy spy;

  public IndirectMemory8BitReference(BaseImmutableOpcodeReference target, Memory memory, InstructionSpy spy) {
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

  public String toString() {
    return "(" + target + ")";
  }

  public int getLength() {
    return target.getLength();
  }

  public Object clone() throws CloneNotSupportedException {
    return new IndirectMemory8BitReference((ImmutableOpcodeReference) target.clone(), memory, spy);
  }
}
