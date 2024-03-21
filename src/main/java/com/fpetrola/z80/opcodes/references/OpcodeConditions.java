package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.registers.Register;

public class OpcodeConditions {

  private Register register;

  public OpcodeConditions(Register flag) {
    register = flag;
  }

  public static Condition t() {
    return new ConditionAlwaysTrue();
  }

  public Condition f(int flag) {
    return new ConditionFlag(register, flag, false);
  }

  public Condition nf(int flag) {
    return new ConditionFlag(register, flag, true);
  }
}
