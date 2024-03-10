package com.fpetrola.z80.instructions.transformations;

import com.fpetrola.z80.opcodes.references.WordNumber;

import java.util.function.Supplier;

public class VirtualAssignmentInstruction<T extends WordNumber> extends DummyInstruction<T> {
  private final Virtual8BitsRegister virtual8BitsRegister;
  private final Supplier<T> lastValueSupplier;

  public VirtualAssignmentInstruction(Virtual8BitsRegister virtual8BitsRegister, Supplier<T> lastValueSupplier) {
    this.virtual8BitsRegister = virtual8BitsRegister;
    this.lastValueSupplier = lastValueSupplier;
  }

  public int execute() {
    virtual8BitsRegister.write(lastValueSupplier.get());
    return 0;
  }

  public String toString() {
    return "virtual loading: " + virtual8BitsRegister + " <- " + lastValueSupplier.toString();
  }
}
