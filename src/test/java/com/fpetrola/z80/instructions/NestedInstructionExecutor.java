package com.fpetrola.z80.instructions;

import com.fpetrola.z80.instructions.base.Instruction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NestedInstructionExecutor {
  private List<Instruction> instructions = new ArrayList<>();

  public Optional<Boolean> execute(Instruction instruction) {
    if (instructions.contains(instruction))
      return Optional.empty();
    else {
      instructions.add(instruction);
      instruction.execute();
      return Optional.of(true);
    }
  }

  public void evicted(Instruction instruction) {
    instructions = instructions.subList(instructions.indexOf(instruction), instructions.size() - 1);
  }
}
