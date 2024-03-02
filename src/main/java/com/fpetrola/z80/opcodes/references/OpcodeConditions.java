package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.registers.flag.FlagRegister;

public class OpcodeConditions {

  private FlagRegister flagRegister;

  public OpcodeConditions(FlagRegister flag) {
    flagRegister = flag;
  }

  public static Condition t() {
    return new ConditionAlwaysTrue();
  }

  public Condition f(int flag) {
    return new ConditionFlag(flagRegister, flag, false);
  }

  public Condition nf(int flag) {
    return new ConditionFlag(flagRegister, flag, true);
  }
}
