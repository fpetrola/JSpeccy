package com.fpetrola.z80.instructions.base;

import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.spy.WrapperInstructionSpy;

import java.util.ArrayList;
import java.util.List;

public class RegisterTransformerInstructionSpy<T extends WordNumber> extends WrapperInstructionSpy<T> {
  public List<Instruction<T>> getExecutedInstructions() {
    return executedInstructions;
  }

  private List<Instruction<T>> executedInstructions = new ArrayList<>();

  public RegisterTransformerInstructionSpy() {
  }

  public void beforeExecution(Instruction<T> instruction) {
    executedInstructions.add(instruction);
    super.beforeExecution(instruction);
  }
}
