package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.ImmutableOpcodeReference;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

public class RepeatingInstruction<T extends WordNumber> extends AbstractInstruction<T> {
  protected Instruction<T> instructionToRepeat;
  private final ImmutableOpcodeReference<T> pc;
  private final Register<T> b;
  protected final Register<T> bc;

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
}
