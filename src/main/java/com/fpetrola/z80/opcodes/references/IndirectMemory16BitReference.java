package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.spy.InstructionSpy;

public final class IndirectMemory16BitReference implements OpcodeReference {

  public final OpcodeReference target;
  private final Memory memory;
  private InstructionSpy spy;

  public IndirectMemory16BitReference(OpcodeReference target, Memory memory, InstructionSpy spy) {
    this.target = target;
    this.memory = memory;
    this.spy = spy;
  }

  public int read() {
    spy.pause();
    int address = target.read();
    spy.doContinue();
    int lsb = memory.read(address) & 0xff;
    int result = (memory.read(address + 1) << 8) & 0xff00 | lsb;
    return result;
  }

  public void write(int value) {
    spy.pause();
    int address = target.read();
    spy.doContinue();
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
    return new IndirectMemory16BitReference((OpcodeReference) target.clone(), memory, spy);
  }
}
