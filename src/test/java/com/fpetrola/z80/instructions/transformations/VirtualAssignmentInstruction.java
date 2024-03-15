package com.fpetrola.z80.instructions.transformations;

import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

import java.util.function.Supplier;

public class VirtualAssignmentInstruction<T extends WordNumber> extends DummyInstruction<T> {
  private final Register register;
  private final Supplier<VirtualRegister<T>> lastRegister;

  public VirtualAssignmentInstruction(Register register, Supplier<VirtualRegister<T>> lastRegister) {
    this.register = register;
    this.lastRegister = lastRegister;
  }

  public int execute() {
    register.write(lastRegister.get().read());
    return 0;
  }

  public String toString() {
    return "virtual loading: " + register + " <- " + lastRegister.toString();
  }
}
