package com.fpetrola.z80.jspeccy;

public class CacheInvalidatorMemoryWriteListener implements MemoryWriteListener {
  public CacheInvalidatorMemoryWriteListener(Runnable[] cacheInvalidators) {
    this.cacheInvalidators = cacheInvalidators;
  }

  private Runnable[] cacheInvalidators;

  @Override
  public void writtingMemoryAt(int address, int value) {
    Runnable cacheInvalidator = cacheInvalidators[address];
    if (cacheInvalidator != null)
      cacheInvalidator.run();
  }
}
