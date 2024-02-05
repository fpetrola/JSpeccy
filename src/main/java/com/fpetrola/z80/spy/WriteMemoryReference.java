package com.fpetrola.z80.spy;

import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.mmu.Memory;

public class WriteMemoryReference extends AbstractSpyReference implements Undoable {

  public int address;
  public int value;
  transient private Memory memory;
  private int lastValue;

  public WriteMemoryReference() {
  }

  public WriteMemoryReference(int address, int value, Memory memory, boolean indirectReference) {
    this.address = address;
    this.value = value;
    this.memory = memory;
    this.indirectReference = indirectReference;
    lastValue = memory.read(address);
  }

  public String toString() {
    return "mem(" + Helper.convertToHex(this.address) + "):= " + this.value + (indirectReference ? " (I)" : "");
  }

  @Override
  public void undo() {
    memory.write(address, lastValue);
  }
}
