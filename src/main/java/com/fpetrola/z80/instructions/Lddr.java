package com.fpetrola.z80.instructions;

import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public class Lddr<T extends WordNumber> extends RepeatingInstruction<T> {
  Lddr(ImmutableOpcodeReference<T> pc, Register<T> b, Register<T> bc, InstructionFactory instructionFactory) {
    super(instructionFactory.Ldd(), pc, b, bc);
  }

  protected boolean checkLoopCondition() {
    return bc.read().isNotZero();
  }
}
