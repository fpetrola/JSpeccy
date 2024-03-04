package com.fpetrola.z80.cpu;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.spy.InstructionSpy;

public class SpyInstructionExecutor<T extends WordNumber> implements InstructionExecutor<T> {
  private InstructionSpy spy;

  public SpyInstructionExecutor(InstructionSpy spy) {
    this.spy = spy;
  }

  @Override
  public void execute(Instruction<T> instruction) {
    spy.beforeExecution(instruction);
    instruction.execute();
    spy.afterExecution(instruction);
  }
}
