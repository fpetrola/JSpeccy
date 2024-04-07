package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.mmu.Memory;

public final class IndirectMemory16BitReference<T extends WordNumber> implements OpcodeReference<T> {
  public ImmutableOpcodeReference<T> target;

  public Memory<T> getMemory() {
    return memory;
  }

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

  public void accept(InstructionVisitor instructionVisitor) {
    instructionVisitor.visitIndirectMemory16BitReference(this);
  }
}
