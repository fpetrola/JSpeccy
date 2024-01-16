package com.fpetrola.z80.spy;

import com.fpetrola.z80.opcodes.references.MemoryPlusRegister8BitReference;

public class MemoryPlusRegister8BitReferenceSpy extends MemoryPlusRegister8BitReference {

  private MemoryPlusRegister8BitReference memoryPlusRegister8BitReference;

  public MemoryPlusRegister8BitReferenceSpy(MemoryPlusRegister8BitReference memoryPlusRegister8BitReference) {
    super();
    this.memoryPlusRegister8BitReference = memoryPlusRegister8BitReference;
  }

  public int read() {
    return memoryPlusRegister8BitReference.read();
  }

  public void write(int value) {
    memoryPlusRegister8BitReference.write(value);
  }

  public int cyclesCost() {
    return memoryPlusRegister8BitReference.cyclesCost();
  }

  public String toString() {
    return memoryPlusRegister8BitReference.toString();
  }

  public int getLength() {
    return memoryPlusRegister8BitReference.getLength();
  }

  public Object clone() throws CloneNotSupportedException {
    return new MemoryPlusRegister8BitReferenceSpy((MemoryPlusRegister8BitReference) memoryPlusRegister8BitReference.clone());
  }
}
