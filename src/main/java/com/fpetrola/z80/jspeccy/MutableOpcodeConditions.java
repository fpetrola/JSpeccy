package com.fpetrola.z80.jspeccy;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.Condition;
import com.fpetrola.z80.opcodes.references.ConditionAlwaysTrue;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;

public class MutableOpcodeConditions extends OpcodeConditions {
  public MutableOpcodeConditions(State state2) {
    super(state2);
  }

  public Condition t() {
    return new ConditionAlwaysTrue();
  }

  public Condition f(int flag) {
    return new FlipFLopConditionFlag();
  }

  public Condition nf(int flag) {
    return new FlipFLopConditionFlag();
  }
}
