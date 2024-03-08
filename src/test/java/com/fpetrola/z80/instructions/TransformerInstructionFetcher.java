package com.fpetrola.z80.instructions;

import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.instructions.cache.InstructionCloner;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.FlagRegister;

public class TransformerInstructionFetcher<T extends WordNumber> extends InstructionFetcherForTest<T> {
  private InstructionCloner<T> instructionCloner;
  private final RegisterTransformerInstructionSpy spy;
  private FlagRegister<T> flag;

  public TransformerInstructionFetcher(State<T> state, InstructionExecutor instructionExecutor, CPUExecutionContext<T> context, RegisterTransformerInstructionSpy spy) {
    super(state, instructionExecutor);
    this.spy = spy;
    flag = state.getFlag();
  }

  public void fetchNextInstruction() {
    int pcValue = pc.read().intValue();
    Instruction<T> instruction = instructions.get(pcValue);
    processTargetSource(instruction, pcValue);

    updatePC(instruction);
  }

  private void processTargetSource(Instruction<T> instruction, int pcValue) {
    Instruction<T> cloned = spy.processInstruction(instruction);

    spy.enable();
    cloned.execute();
    spy.disable();

    if (!(((TargetInstruction) cloned).getTarget() instanceof Register))
      cloned.execute();
  }
}
