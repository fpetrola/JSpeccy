package com.fpetrola.z80.instructions.transformations;

import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public class VirtualAssignmentInstruction<T extends WordNumber> extends DummyInstruction<T> {
  private final Register register;
  private final VirtualRegister<T> lastRegister;

  public VirtualAssignmentInstruction(Register register, VirtualRegister<T> lastRegister) {
    this.register = register;
    this.lastRegister = lastRegister;
  }

  public int execute() {
    register.write(lastRegister.read());
    return 0;
  }

  public String toString() {
    return "virtual loading: " + register + " <- " + lastRegister.toString();
  }
}
