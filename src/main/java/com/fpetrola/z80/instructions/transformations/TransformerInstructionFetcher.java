package com.fpetrola.z80.instructions.transformations;

import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.instructions.Ld;
import com.fpetrola.z80.instructions.base.ConditionalInstruction;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.flag.FlagRegister;

import java.util.HashMap;
import java.util.Map;

public class TransformerInstructionFetcher<T extends WordNumber> extends InstructionFetcherForTest<T> {
  private InstructionTransformer<T> instructionTransformer;
  private FlagRegister<T> flag;
  private Map<Instruction<T>, Instruction<T>> clonedInstructions = new HashMap<>();
  private InstructionActionExecutor<T> resetter = new InstructionActionExecutor<>(r -> r.reset());

  public TransformerInstructionFetcher(State<T> state, InstructionExecutor instructionExecutor, InstructionTransformer instructionTransformer) {
    super(state, instructionExecutor);
    flag = state.getFlag();
    this.instructionTransformer = instructionTransformer;
  }

  public void fetchNextInstruction() {
    int pcValue = pc.read().intValue();
    Instruction<T> instruction = instructions.get(pcValue);
    Instruction<T> cloned = processTargetSource(instruction, pcValue);

    updatePC(cloned);
  }

  private Instruction<T> processTargetSource(Instruction<T> instruction, int pcValue) {
    instructionTransformer.virtualRegisterFactory.getRegisterNameBuilder().setCurrentAddress(getAddressOf(instruction));

    instructionTransformer.setCurrentInstruction(instruction);
    Instruction<T> cloned = instructionTransformer.clone(instruction);
    Instruction<T> tInstruction = clonedInstructions.get(instruction);
    if (tInstruction == null)
      clonedInstructions.put(instruction, cloned);
    else
      cloned = tInstruction;

    resetter.executeAction(cloned);

    if (isConcreteInstruction(cloned))
      instructionExecutor.execute(cloned);

    return cloned;
  }

  private boolean isConcreteInstruction(Instruction<T> cloned) {
    boolean concreteInstruction = cloned instanceof Ld && !(((Ld) cloned).getTarget() instanceof Register);
    concreteInstruction |= cloned instanceof ConditionalInstruction;
    return concreteInstruction;
  }

  public int getAddressOf(Instruction instruction) {
    return instructions.indexOf(instruction);
  }
}
