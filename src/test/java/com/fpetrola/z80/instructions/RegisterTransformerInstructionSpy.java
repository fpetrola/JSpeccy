package com.fpetrola.z80.instructions;

import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.spy.AbstractInstructionSpy;

public class RegisterTransformerInstructionSpy<T extends WordNumber> extends AbstractInstructionSpy<T> {
  public RegisterTransformerInstructionSpy() {
  }

  public void enable() {
    super.enable();
  }

  public void disable() {
    super.disable();
  }
}
