package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.jspeccy.ConditionPredicate;
import com.fpetrola.z80.registers.Register;

import java.util.function.Predicate;

public class BNotZeroCondition<T extends WordNumber> extends ConditionBase {
  public void setB(Register<T> b) {
    this.b = b;
  }

  private Register<T> b;

  public BNotZeroCondition(Register<T> b, ConditionPredicate<Boolean> predicate) {
    super(predicate);
    this.b = b;
  }

  public BNotZeroCondition(Register<T> b) {
    this(b, (b1, i) -> b1);
  }

  public boolean conditionMet(Instruction instruction) {
    return filterCondition(b.read().isNotZero(), instruction);
  }

  public Register<T> getB() {
    return b;
  }

  public void accept(InstructionVisitor visitor) {
    visitor.visitBNotZeroCondition(this);
  }
}
