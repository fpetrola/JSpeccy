package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.RepeatingInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Flags;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;

public class Cpdr<T extends WordNumber> extends RepeatingInstruction<T> {
  private final Register<T> flag;

  public Cpdr(ImmutableOpcodeReference<T> pc, RegisterPair<T> bc, Register<T> flag, Cpd cpd) {
    super(cpd, pc, bc);
    this.flag = flag;
  }

  protected boolean checkLoopCondition() {
    return !((flag.read().intValue() & Flags.ZERO_FLAG) != 0) && bc.read().isNotZero();
  }

  @Override
  public void accept(InstructionVisitor visitor) {
    super.accept(visitor);
    visitor.visitCpdr(this);
  }
}
