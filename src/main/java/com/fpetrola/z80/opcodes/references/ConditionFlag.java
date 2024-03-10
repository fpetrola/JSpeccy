package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.instructions.base.InstructionVisitor;
import com.fpetrola.z80.registers.Flags;
import com.fpetrola.z80.registers.Register;

public class ConditionFlag<T extends WordNumber> implements Condition {
  private Register<T> register;

  public int getFlag() {
    return flag;
  }

  private final int flag;

  public boolean isNegate() {
    return negate;
  }

  private final boolean negate;

  public ConditionFlag(Register register, int flag, boolean negate) {
    this.register = register;
    this.flag = flag;
    this.negate = negate;
  }

  public boolean conditionMet() {
    if (!negate) {
      return ((register.read().intValue() & flag) == flag);
    } else {
      return !((register.read().intValue() & flag) == flag);
    }
  }

  public Register<T> getRegister() {
    return register;
  }

  public void setRegister(Register<T> register) {
    this.register = register;
  }

  public void accept(InstructionVisitor visitor) {
    visitor.visitingConditionFlag(this);
  }

  public String toString() {
    return ((negate) ? "N" : "") + Flags.toString(flag);
  }
}
