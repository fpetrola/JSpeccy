package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.Memory;

public class WriteMemoryReference implements Undoable {

  public int address;
  public int value;
  private Memory memory;
  private int lastValue;

  public WriteMemoryReference(int address, int value, Memory memory) {
    this.address = address;
    this.value = value;
    this.memory = memory;
    lastValue = memory.read(address);
  }

  public String toString() {
    return "mem(" + this.address + "):= " + this.value;
  }

  @Override
  public void undo() {
    memory.write(address, lastValue);
  }

}
