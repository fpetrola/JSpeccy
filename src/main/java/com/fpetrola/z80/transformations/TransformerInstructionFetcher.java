package com.fpetrola.z80.transformations;

import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.instructions.Ld;
import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

import java.util.HashMap;
import java.util.Map;

public class TransformerInstructionFetcher<T extends WordNumber> extends InstructionFetcherForTest<T> {
  private final TransformerInstructionExecutor<T> instructionExecutor1;

  public TransformerInstructionFetcher(State<T> state, TransformerInstructionExecutor instructionExecutor) {
    super(state, instructionExecutor);
    instructionExecutor1 = instructionExecutor;
  }

  public void fetchNextInstruction() {
    Instruction<T> instruction = instructions.get(pc.read().intValue());
    Instruction cloned = instructionExecutor.execute(instruction);
    updatePC(cloned);
  }

  private boolean isConcreteInstruction(Instruction<T> cloned) {
    boolean concreteInstruction = cloned instanceof Ld && !(((Ld) cloned).getTarget() instanceof Register);
    concreteInstruction |= cloned instanceof ConditionalInstruction;
    return concreteInstruction;
  }

  @Override
  public Instruction<T> getTransformedInstructionAt(int i) {
    return instructionExecutor1.clonedInstructions.get(getInstructionAt(i));
  }
}
