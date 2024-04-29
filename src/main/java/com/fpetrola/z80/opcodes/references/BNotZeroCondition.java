package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.registers.Register;

import java.util.function.Predicate;

public class BNotZeroCondition<T extends WordNumber> extends ConditionBase {
  public void setB(Register<T> b) {
    this.b = b;
  }

  private Register<T> b;

  public BNotZeroCondition(Register<T> b, Predicate<Boolean> predicate) {
    super(predicate);
    this.b = b;
  }

  public BNotZeroCondition(Register<T> b) {
    this(b, b1 -> b1);
  }

  public boolean conditionMet() {
    return filterCondition(b.read().isNotZero());
  }

  public Register<T> getB() {
    return b;
  }

  public void accept(InstructionVisitor visitor) {
    visitor.visitBNotZeroCondition(this);
  }
}
