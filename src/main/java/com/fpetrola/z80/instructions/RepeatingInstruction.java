package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.flag.IFlagRegister;

import static com.fpetrola.z80.registers.RegisterName.F;
import static com.fpetrola.z80.registers.RegisterName.HL;

public class RepeatingInstruction<T extends WordNumber> extends AbstractInstruction<T> {
  protected Instruction<T> instructionToRepeat;

  public RepeatingInstruction(State state, Instruction<T> instructionToRepeat) {
    super(state, state.getRegister(HL), (IFlagRegister) state.getRegister(F));
    this.instructionToRepeat = instructionToRepeat;
  }

  public int execute() {
    int execute = instructionToRepeat.execute();
    setNextPC(checkLoopCondition() ? pc.read() : null);
    return execute;
  }

  protected boolean checkLoopCondition() {
    return b.read().isNotZero();
  }
}
