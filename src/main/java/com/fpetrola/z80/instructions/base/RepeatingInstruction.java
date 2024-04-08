package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterPair;

public class RepeatingInstruction<T extends WordNumber> extends AbstractInstruction<T> {
  protected Instruction<T> instructionToRepeat;
  private  ImmutableOpcodeReference<T> pc;
  protected  RegisterPair<T> bc;

  public RepeatingInstruction(Instruction<T> instructionToRepeat, ImmutableOpcodeReference<T> pc, RegisterPair<T> bc) {
    this.instructionToRepeat = instructionToRepeat;
    this.pc = pc;
    this.bc = bc;
  }

  public int execute() {
    int execute = instructionToRepeat.execute();
    setNextPC(checkLoopCondition() ? pc.read() : null);
    return execute;
  }

  protected boolean checkLoopCondition() {
    return bc.getHigh().read().isNotZero();
  }

  @Override
  public void accept(InstructionVisitor visitor) {
    super.accept(visitor);
    visitor.visitRepeatingInstruction(this);
  }

  public Instruction<T> getInstructionToRepeat() {
    return instructionToRepeat;
  }

  public void setInstructionToRepeat(Instruction<T> instructionToRepeat) {
    this.instructionToRepeat = instructionToRepeat;
  }

  public ImmutableOpcodeReference<T> getPc() {
    return pc;
  }

  public void setPc(ImmutableOpcodeReference<T> pc) {
    this.pc = pc;
  }

  public RegisterPair<T> getBc() {
    return bc;
  }

  public void setBc(RegisterPair<T> bc) {
    this.bc = bc;
  }
}
