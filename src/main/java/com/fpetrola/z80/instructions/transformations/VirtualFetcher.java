package com.fpetrola.z80.instructions.transformations;

import com.fpetrola.z80.opcodes.references.WordNumber;
import java.util.function.Supplier;

public class VirtualFetcher<T extends WordNumber> {
  private boolean executing;

  public T readFromVirtual(Runnable instructionRunner, Supplier<T> resultSupplier, Supplier<T> lastValueSupplier) {
    T t = resultSupplier.get();
    if (t != null)
      return t;
    else if (!executing) {
      executing = true;
      instructionRunner.run();
      executing = false;
      return resultSupplier.get();
    } else
      return lastValueSupplier.get();
  }
}
