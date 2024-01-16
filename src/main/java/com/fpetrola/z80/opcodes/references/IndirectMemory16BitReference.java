package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.mmu.Memory;

public final class IndirectMemory16BitReference implements OpcodeReference {

  public final OpcodeReference target;
  private final Memory memory;

  public IndirectMemory16BitReference(OpcodeReference target, Memory memory) {
    this.target = target;
    this.memory = memory;
  }

  public int read() {
    int address = target.read();
    int lsb = memory.read(address) & 0xff;
    return ((memory.read(address + 1) << 8) & 0xff00 | lsb);
  }

  public void write(int value) {
    int address = target.read();
    memory.write(address, value & 0xFF);
    memory.write(address + 1, (value >> 8));
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
    return new IndirectMemory16BitReference((OpcodeReference) target.clone(), memory);
  }
}
