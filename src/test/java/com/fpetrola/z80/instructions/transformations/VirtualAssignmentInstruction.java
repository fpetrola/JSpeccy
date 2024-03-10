package com.fpetrola.z80.instructions.transformations;

import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

import java.util.function.Supplier;

public class VirtualAssignmentInstruction<T extends WordNumber> extends DummyInstruction<T> {
  private final Register register;
  private final Supplier<T> lastValueSupplier;

  public VirtualAssignmentInstruction(Register register, Supplier<T> lastValueSupplier) {
    this.register = register;
    this.lastValueSupplier = lastValueSupplier;
  }

  public int execute() {
    register.write(lastValueSupplier.get());
    return 0;
  }

  public String toString() {
    return "virtual loading: " + register + " <- " + lastValueSupplier.toString();
  }
}
