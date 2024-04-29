package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.opcodes.references.Condition;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public class DJNZ<T extends WordNumber> extends ConditionalInstruction<T> {
  private Register<T> b;

  public DJNZ(ImmutableOpcodeReference<T> target, Condition condition, Register<T> b, Register<T> pc) {
    super(target, null, pc);
    this.b = b;
    this.condition = () -> this.b.read().isNotZero();
  }

  public int execute() {
    b.decrement();
    return super.execute();
  }

  public T calculateJumpAddress() {
    return calculateRelativeJumpAddress();
  }

  public Register<T> getB() {
    return b;
  }

  public void setB(Register<T> b) {
    this.b = b;
  }

  public void accept(InstructionVisitor visitor) {
    super.accept(visitor);
    visitor.visitingDjnz(this);
  }

  public String toString() {
    return getClass().getSimpleName() + " " + positionOpcodeReference;
  }
}
