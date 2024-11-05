package com.fpetrola.z80.jspeccy;

import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class MemorySetter {
  private final Memory<? extends WordNumber> memory;

  public <T extends WordNumber> MemorySetter(Memory<T> memory) {
    this.memory = memory;
  }

  public void setData(byte[] result) {
    for (int i = 0; i <result.length; i++) {
      memory.getData()[i]= WordNumber.createValue(result[i]);
    }
  }
}
