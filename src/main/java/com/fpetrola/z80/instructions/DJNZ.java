package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public class DJNZ<T extends WordNumber> extends ConditionalInstruction<T> {
  private Register<T> b;

  public DJNZ(ImmutableOpcodeReference<T> target, Register<T> b, Register<T> pc) {
    super(target, () -> b.read().isNotZero(), pc);
    this.b = b;
  }

  public int execute() {
    b.decrement();
    return super.execute();
  }

  protected T calculateJumpAddress() {
    return calculateRelativeJumpAddress();
  }
}
