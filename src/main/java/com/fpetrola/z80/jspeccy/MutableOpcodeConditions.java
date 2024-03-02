package com.fpetrola.z80.jspeccy;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.Condition;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;

public class MutableOpcodeConditions extends OpcodeConditions {
  public MutableOpcodeConditions(State state2) {
    super(state2.getFlag());
  }

  public Condition f(int flag) {
    return new FlipFLopConditionFlag(super.f(flag));
  }

  public Condition nf(int flag) {
    return new FlipFLopConditionFlag(super.nf(flag));
  }
}
