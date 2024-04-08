package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.instructions.base.RepeatingInstruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;

public class Ldir<T extends WordNumber> extends RepeatingInstruction<T> {
  public Ldir(ImmutableOpcodeReference<T> pc, RegisterPair<T> bc, Ldi ldi) {
    super(ldi, pc, bc);
  }

  protected boolean checkLoopCondition() {
    return bc.read().isNotZero();
  }

  @Override
  public void accept(InstructionVisitor visitor) {
    super.accept(visitor);
    visitor.visitLdir(this);
  }
}
