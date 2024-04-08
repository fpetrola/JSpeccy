package com.fpetrola.z80.cpu;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.spy.InstructionSpy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SpyInstructionExecutor<T extends WordNumber> implements InstructionExecutor<T> {
  private InstructionSpy spy;
  private Set<Instruction<T>> executingInstructions = new HashSet<>();

  public SpyInstructionExecutor(InstructionSpy spy) {
    this.spy = spy;
  }

  @Override
  public Instruction<T> execute(Instruction<T> instruction) {
    spy.beforeExecution(instruction);
    executingInstructions.add(instruction);
    instruction.execute();
    executingInstructions.remove(instruction);
    spy.afterExecution(instruction);
    return instruction;
  }

  @Override
  public boolean isExecuting(Instruction<T> instruction) {
    return executingInstructions.contains(instruction);
  }
}
