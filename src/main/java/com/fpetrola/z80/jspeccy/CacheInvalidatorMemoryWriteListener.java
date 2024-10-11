package com.fpetrola.z80.jspeccy;

import com.fpetrola.z80.opcodes.references.WordNumber;

public class CacheInvalidatorMemoryWriteListener<T extends WordNumber>  implements MemoryWriteListener<T> {
  public CacheInvalidatorMemoryWriteListener(Runnable[] cacheInvalidators) {
    this.cacheInvalidators = cacheInvalidators;
  }

  private Runnable[] cacheInvalidators;

  @Override
  public void writtingMemoryAt(T address, T value) {
    Runnable cacheInvalidator = cacheInvalidators[address.intValue()];
    if (cacheInvalidator != null)
      cacheInvalidator.run();
  }
}
