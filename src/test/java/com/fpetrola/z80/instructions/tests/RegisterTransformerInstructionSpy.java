package com.fpetrola.z80.instructions.tests;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.spy.AbstractInstructionSpy;

import java.util.ArrayList;
import java.util.List;

public class RegisterTransformerInstructionSpy<T extends WordNumber> extends AbstractInstructionSpy<T> {
  public List<Instruction<T>> getExecutedInstructions() {
    return executedInstructions;
  }

  private List<Instruction<T>> executedInstructions = new ArrayList<>();

  public RegisterTransformerInstructionSpy() {
  }

  public void enable() {
    super.enable();
  }

  public void disable() {
    super.disable();
  }

  public void beforeExecution(Instruction<T> instruction) {
    executedInstructions.add(instruction);
    super.beforeExecution(instruction);
  }
}
