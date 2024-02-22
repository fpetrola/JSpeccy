package com.fpetrola.z80.instructions;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public class Cpdr<T extends WordNumber> extends RepeatingInstruction<T> {
  Cpdr(ImmutableOpcodeReference<T> pc, Register<T> b, Register<T> bc) {
    super(InstructionFactory.createCpd(), pc, b, bc);
  }

  protected boolean checkLoopCondition() {
    return !flag.getZ() && bc.read().isNotZero();
  }
}
