package com.fpetrola.z80.jspeccy;

import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.*;
import com.fpetrola.z80.registers.RegisterName;

public class MutableOpcodeConditions extends OpcodeConditions {
  private Runnable executionsListener;

  public MutableOpcodeConditions(State state2, Runnable executionsListener) {
    super(state2.getFlag(), state2.getRegister(RegisterName.B));
    this.executionsListener = executionsListener;
  }

  public ConditionFlag f(int flag) {
    ConditionFlag f = super.f(flag);
    FlipFLopConditionFlag flipFLopConditionFlag = new FlipFLopConditionFlag(executionsListener);
    f.isConditionMet = flipFLopConditionFlag.isConditionMet;
    return f;
  }

  public ConditionFlag nf(int flag) {
    ConditionFlag f = super.nf(flag);
    FlipFLopConditionFlag flipFLopConditionFlag = new FlipFLopConditionFlag(executionsListener);
    f.isConditionMet = flipFLopConditionFlag.isConditionMet;
    return f;
  }

  @Override
  public BNotZeroCondition bnz() {
    BNotZeroCondition f = super.bnz();
    FlipFLopConditionFlag flipFLopConditionFlag = new FlipFLopConditionFlag(executionsListener);
    f.isConditionMet = flipFLopConditionFlag.isConditionMet;
    return f;
  }
}
