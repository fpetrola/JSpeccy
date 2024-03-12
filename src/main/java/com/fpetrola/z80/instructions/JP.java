package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.Register;

public class JP<T extends WordNumber> extends ConditionalInstruction<T> {
  public JP(ImmutableOpcodeReference target, Condition condition, Register<T> pc) {
    super(target, condition, pc);
  }
}
