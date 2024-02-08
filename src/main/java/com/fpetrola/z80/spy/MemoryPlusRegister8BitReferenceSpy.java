package com.fpetrola.z80.spy;

import com.fpetrola.z80.opcodes.references.MemoryPlusRegister8BitReference;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class MemoryPlusRegister8BitReferenceSpy<T extends WordNumber> extends MemoryPlusRegister8BitReference<T> {

  private MemoryPlusRegister8BitReference<T> memoryPlusRegister8BitReference;

  public MemoryPlusRegister8BitReferenceSpy(MemoryPlusRegister8BitReference memoryPlusRegister8BitReference) {
    super();
    this.memoryPlusRegister8BitReference = memoryPlusRegister8BitReference;
  }

  public T read() {
    return memoryPlusRegister8BitReference.read();
  }

  public void write(T value) {
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
