package com.fpetrola.z80.instructions;

import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.IFlagRegister;

public class Cpir<T extends WordNumber> extends RepeatingInstruction<T> {
  private final IFlagRegister<T> flag;
  private final Register<T> bc;

  Cpir(IFlagRegister<T> flag, Register<T> bc, ImmutableOpcodeReference<T> pc, Register<T> b, InstructionFactory instructionFactory) {
    super(instructionFactory.Cpi(), pc, b, bc);
    this.flag = flag;
    this.bc = bc;
  }

  protected boolean checkLoopCondition() {
    return !flag.getZ() && bc.read().isNotZero();
  }
}
