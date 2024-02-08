package com.fpetrola.z80.opcodes.references;

import com.fpetrola.z80.registers.Flags;
import com.fpetrola.z80.registers.Register;

public class ConditionFlag<T extends WordNumber> implements Condition {

  private final Register<T> r;
  private final int flag;
  private final boolean negate;

  public ConditionFlag(Register r, int flag, boolean negate) {
    this.r = r;
    this.flag = flag;
    this.negate = negate;
  }

  public boolean conditionMet() {
    if (!negate) {
      return ((r.read().intValue() & flag) == flag);
    } else {
      return !((r.read().intValue() & flag) == flag);
    }
  }

  public String toString() {
    return ((negate) ? "N" : "") + Flags.toString(flag);
  }
}
