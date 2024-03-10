package com.fpetrola.z80.instructions.transformations;

import java.util.function.Supplier;

public class VirtualFetcher<T> {
  private boolean executing;

  public T readFromVirtual(Runnable instructionRunner, Supplier<T> resultSupplier, Supplier<T> lastValueSupplier) {
    if (!executing) {
      executing = true;
      instructionRunner.run();
      executing = false;
      return resultSupplier.get();
    } else
      return lastValueSupplier.get();
  }
}
