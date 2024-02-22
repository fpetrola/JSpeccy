package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Register;

public class JR<T extends WordNumber> extends ConditionalInstruction<T> {

  JR(ImmutableOpcodeReference target, Condition condition, Register<T> pc1) {
    super(target, condition, pc1);
  }

  protected T calculateJumpAddress() {
    return calculateRelativeJumpAddress();
  }
}
