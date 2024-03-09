package com.fpetrola.z80.instructions.transformations;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Plain8BitRegister;

import java.util.function.Supplier;

public class VirtualPlain8BitRegister<T extends WordNumber> extends Plain8BitRegister<T> {
  private final boolean[] semaphore;
  private final Instruction<T> instruction;
  private final Supplier<T> lastValueSupplier;

  public VirtualPlain8BitRegister(String name, boolean[] semaphore, Instruction<T> instruction, Supplier<T> lastValueSupplier) {
    super(name);
    this.semaphore = semaphore;
    this.instruction = instruction;
    this.lastValueSupplier = lastValueSupplier;
  }

  public T read() {
    if (data != null)
      return data;

    if (!semaphore[0]) {
      semaphore[0] = true;
      instruction.execute();
      semaphore[0] = false;
      return (T) data;
    } else
      return (T) lastValueSupplier.get();
  }
}
