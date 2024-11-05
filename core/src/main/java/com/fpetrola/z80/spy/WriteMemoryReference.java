package com.fpetrola.z80.spy;

import com.fpetrola.z80.helpers.Helper;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class WriteMemoryReference<T extends WordNumber> extends AbstractSpyReference<T> implements Undoable {

  public T address;
  public T value;
  transient private Memory<T> memory;
  private T lastValue;

  public WriteMemoryReference() {
  }

  public WriteMemoryReference(T address, T value, Memory<T> memory, boolean indirectReference) {
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
