package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.registers.Register;

public class OpcodeConditions {
  protected Register register;
  protected Register b;

  public OpcodeConditions(Register flag, Register b) {
    register = flag;
    this.b = b;
  }

  public ConditionAlwaysTrue t() {
    return new ConditionAlwaysTrue();
  }

  public ConditionFlag f(int flag) {
    return new ConditionFlag(register, flag, false);
  }

  public ConditionFlag nf(int flag) {
    return new ConditionFlag(register, flag, true);
  }

  public BNotZeroCondition bnz() {
    return new BNotZeroCondition(b);
  }
}
