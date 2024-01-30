package com.fpetrola.z80.spy;

import com.fpetrola.z80.helpers.StringHelper;
import com.fpetrola.z80.mmu.Memory;

public class ReadMemoryReference extends AbstractSpyReference implements Undoable {
  public int address;
  public int value;
  transient private Memory memory;

  public ReadMemoryReference() {
  }

  public ReadMemoryReference(int address, int value, Memory memory, boolean indirectReference) {
    this.address = address;
    this.value = value;
    this.memory = memory;
    this.indirectReference = indirectReference;
  }

  public String toString() {
    return value + "= mem(" + StringHelper.convertToHex(this.address) + ")" + (indirectReference ? " (I)" : "");
  }

  public void undo() {
  }
}
