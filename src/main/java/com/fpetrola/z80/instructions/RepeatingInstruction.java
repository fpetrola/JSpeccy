package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;

public class RepeatingInstruction extends AbstractInstruction {
  protected Instruction instructionToRepeat;

  public RepeatingInstruction(State state, Instruction instructionToRepeat) {
    super(state);
    this.instructionToRepeat = instructionToRepeat;
  }

  public int execute() {
    instructionToRepeat.setSpy(spy);
    int execute = instructionToRepeat.execute();
    spy.pause();
    setNextPC(checkLoopCondition() ? pc.read() : -1);
    spy.doContinue();
    return execute;
  }

  protected boolean checkLoopCondition() {
    return bc.read() != 0;
  }
}
