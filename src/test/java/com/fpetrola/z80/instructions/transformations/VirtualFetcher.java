package com.fpetrola.z80.instructions.transformations;

import java.util.function.Supplier;

public class VirtualFetcher<T> {
  private boolean executing;

  public T readFromVirtual(Runnable instructionRunner, Supplier<T> resultSupplier, VirtualRegister<T> lastValueSupplier) {
    T t = resultSupplier.get();
    if (t != null)
      return t;
    else if (!executing) {
      executing = true;
      instructionRunner.run();
      executing = false;
      return resultSupplier.get();
    } else
      return lastValueSupplier.read();
  }
}
