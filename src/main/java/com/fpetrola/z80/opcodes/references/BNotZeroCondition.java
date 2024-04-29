package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.registers.Register;

public class BNotZeroCondition<T extends WordNumber> implements Condition {
  public void setB(Register<T> b) {
    this.b = b;
  }

  private  Register<T> b;

  public BNotZeroCondition(Register<T> b) {
    this.b = b;
  }

  public boolean conditionMet() {
    return b.read().isNotZero();
  }

  public Register<T> getB() {
    return b;
  }

  public void accept(InstructionVisitor visitor) {
    visitor.visitBNotZeroCondition(this);
  }
}
