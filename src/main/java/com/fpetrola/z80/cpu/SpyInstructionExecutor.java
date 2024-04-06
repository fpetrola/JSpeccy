package com.fpetrola.z80.cpu;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.spy.InstructionSpy;

public class SpyInstructionExecutor<T extends WordNumber> implements InstructionExecutor<T> {
  private InstructionSpy spy;
  private Instruction<T> executingInstruction;

  public SpyInstructionExecutor(InstructionSpy spy) {
    this.spy = spy;
  }

  @Override
  public Instruction<T> execute(Instruction<T> instruction) {
    spy.beforeExecution(instruction);
    executingInstruction = instruction;
    instruction.execute();
    executingInstruction = null;
    spy.afterExecution(instruction);
    return instruction;
  }

  @Override
  public boolean isExecuting(Instruction<T> instruction) {
    return executingInstruction == instruction;
  }
}
