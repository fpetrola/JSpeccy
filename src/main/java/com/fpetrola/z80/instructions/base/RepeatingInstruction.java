package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public class RepeatingInstruction<T extends WordNumber> extends AbstractInstruction<T> {
  protected Instruction<T> instructionToRepeat;
  private  ImmutableOpcodeReference<T> pc;
  private  Register<T> b;
  protected  Register<T> bc;

  public RepeatingInstruction(Instruction<T> instructionToRepeat, ImmutableOpcodeReference<T> pc, Register<T> b, Register<T> bc) {
    this.instructionToRepeat = instructionToRepeat;
    this.pc = pc;
    this.b = b;
    this.bc = bc;
  }

  public int execute() {
    int execute = instructionToRepeat.execute();
    setNextPC(checkLoopCondition() ? pc.read() : null);
    return execute;
  }

  protected boolean checkLoopCondition() {
    return b.read().isNotZero();
  }

  @Override
  public void accept(InstructionVisitor visitor) {
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

  public Register<T> getB() {
    return b;
  }

  public void setB(Register<T> b) {
    this.b = b;
  }

  public Register<T> getBc() {
    return bc;
  }

  public void setBc(Register<T> bc) {
    this.bc = bc;
  }
}
