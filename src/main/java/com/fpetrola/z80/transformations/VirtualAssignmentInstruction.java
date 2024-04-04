package com.fpetrola.z80.transformations;

import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

import java.util.function.Supplier;

public class VirtualAssignmentInstruction<T extends WordNumber> extends DummyInstruction<T> {
  private final Virtual8BitsRegister register;
  private final Supplier<Virtual8BitsRegister<T>> lastRegister;

  public VirtualAssignmentInstruction(Virtual8BitsRegister register, Supplier<Virtual8BitsRegister<T>> lastRegister) {
    this.register = register;
    this.lastRegister = lastRegister;
  }

  public int execute() {
    Virtual8BitsRegister<T> tVirtualRegister = lastRegister.get();
    register.write(tVirtualRegister.read());
    register.lastVersionRead= tVirtualRegister;
    return 0;
  }

  public Register getRegister() {
    return register;
  }

  public Supplier<Virtual8BitsRegister<T>> getLastRegister() {
    return lastRegister;
  }

  public String toString() {
    return "virtual loading: " + register + " <- " + lastRegister.get().toString();
  }
}
