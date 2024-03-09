package com.fpetrola.z80.instructions.transformations;

import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.instructions.DJNZ;
import com.fpetrola.z80.instructions.InstructionFetcherForTest;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.instructions.cache.InstructionCloner;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class TransformerInstructionFetcher<T extends WordNumber> extends InstructionFetcherForTest<T> {
  private InstructionCloner<T> instructionCloner;
  private FlagRegister<T> flag;
  private TransformerVisitor visitor;

  public TransformerInstructionFetcher(State<T> state, InstructionExecutor instructionExecutor, InstructionCloner instructionCloner) {
    super(state, instructionExecutor);
    flag = state.getFlag();
    this.instructionCloner = instructionCloner;
    visitor = new TransformerVisitor(instructionExecutor);
  }

  public void fetchNextInstruction() {
    int pcValue = pc.read().intValue();
    Instruction<T> instruction = instructions.get(pcValue);
    Instruction<T> cloned = processTargetSource(instruction, pcValue);

    updatePC(cloned);
  }

  private Instruction<T> processTargetSource(Instruction<T> instruction, int pcValue) {
    Instruction<T> cloned = instructionCloner.clone(instruction);

    cloned.accept(visitor);

    if (cloned instanceof DJNZ djnz)
      djnz.execute();
    else if (!(((TargetInstruction) cloned).getTarget() instanceof Register))
      instructionExecutor.execute(cloned);

    return cloned;
  }
}
