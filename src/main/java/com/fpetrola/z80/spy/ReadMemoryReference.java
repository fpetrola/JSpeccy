package com.fpetrola.z80.spy;

import com.fpetrola.z80.OOZ80;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.OpcodeReference;

public class ReadMemoryReference implements Undoable, SpyReference {
  public int address;
  public int value;
  private Memory memory;

  public ReadMemoryReference(int address, int value, Memory memory) {
    this.address = address;
    this.value = value;
    this.memory = memory;
  }

  public String toString() {
    return value + "= mem(" + OOZ80.convertToHex(this.address) + ")";
  }

  public void undo() {
  }

  public OpcodeReference getReference() {
    return new ReadMemoryOpcodeReference(memory, address);
  }
}
