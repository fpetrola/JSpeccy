package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.mmu.Memory;

public final class IndirectMemory8BitReference implements OpcodeReference {

  public final OpcodeReference target;
  private final Memory memory;

  public IndirectMemory8BitReference(OpcodeReference target, Memory memory) {
    this.target = target;
    this.memory = memory;
  }

  public int read() {
    final int value = memory.read(target.read());
    return value;
  }

  public void write(int value) {
    memory.write(target.read(), value);
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
    return new IndirectMemory8BitReference((OpcodeReference) target.clone(), memory);
  }
}
