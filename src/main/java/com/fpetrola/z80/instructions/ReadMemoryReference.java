package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.Memory;

public class ReadMemoryReference implements Undoable {
  public int address;
  public int value;
  private Memory memory;

  public ReadMemoryReference(int address, int value, Memory memory) {
    this.address = address;
    this.value = value;
    this.memory = memory;
  }

  public String toString() {
    return value + "= mem(" + this.address + ")";
  }

  public void undo() {
  }
}
