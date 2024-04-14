package com.fpetrola.z80.transformations;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class TransformerInstructionFetcher<T extends WordNumber> extends InstructionFetcherForTest<T> {
  private final TransformerInstructionExecutor<T> instructionExecutor1;

  public TransformerInstructionFetcher(State<T> state, TransformerInstructionExecutor instructionExecutor) {
    super(state, instructionExecutor);
    instructionExecutor1 = instructionExecutor;
  }

  public void fetchNextInstruction() {
    updatePC(instructionExecutor.execute(instructions.get(pc.read().intValue())));
  }

  public Instruction<T> getTransformedInstructionAt(int i) {
    return instructionExecutor1.clonedInstructions.get(i);
  }
}
