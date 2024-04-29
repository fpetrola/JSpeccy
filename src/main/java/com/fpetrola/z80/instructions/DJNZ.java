package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.opcodes.references.BNotZeroCondition;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public class DJNZ<T extends WordNumber> extends ConditionalInstruction<T, BNotZeroCondition<T>> {
  public DJNZ(ImmutableOpcodeReference<T> target, BNotZeroCondition condition, Register<T> pc) {
    super(target, null, pc);
    this.condition = condition;
  }

  public int execute() {
    condition.getB().decrement();
    return super.execute();
  }

  public T calculateJumpAddress() {
    return calculateRelativeJumpAddress();
  }

  public void accept(InstructionVisitor visitor) {
    super.accept(visitor);
    visitor.visitingDjnz(this);
  }

  public String toString() {
    return getClass().getSimpleName() + " " + positionOpcodeReference;
  }
}
