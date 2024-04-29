package com.fpetrola.z80.jspeccy;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.Condition;
import com.fpetrola.z80.opcodes.references.ConditionFlag;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.registers.RegisterName;

public class MutableOpcodeConditions extends OpcodeConditions {
  private Runnable executionsListener;

  public MutableOpcodeConditions(State state2, Runnable executionsListener) {
    super(state2.getFlag(), state2.getRegister(RegisterName.B));
    this.executionsListener = executionsListener;
  }

  public ConditionFlag f(int flag) {
    return new FlipFLopConditionFlag(register, flag, false, executionsListener);
  }

  public ConditionFlag nf(int flag) {
    return new FlipFLopConditionFlag(register, flag, true, executionsListener);
  }
}
