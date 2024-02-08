package com.fpetrola.z80.spy;

import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class ReadMemoryReference<T extends WordNumber> extends AbstractSpyReference<T> implements Undoable {
  public T address;
  public T value;
  transient private Memory<T> memory;

  public ReadMemoryReference() {
  }

  public ReadMemoryReference(T address, T value, Memory memory, boolean indirectReference) {
    this.address = address;
    this.value = value;
    this.memory = memory;
    this.indirectReference = indirectReference;
  }

  public String toString() {
    return value + "= mem(" + Helper.convertToHex(this.address) + ")" + (indirectReference ? " (I)" : "");
  }

  public void undo() {
  }
}
