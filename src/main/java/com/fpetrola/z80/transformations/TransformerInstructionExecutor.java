package com.fpetrola.z80.transformations;

import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

import java.util.HashMap;
import java.util.Map;

public class TransformerInstructionExecutor<T extends WordNumber> implements InstructionExecutor<T> {
  private final Register<T> pc;
  private InstructionExecutor<T> instructionExecutor;

  public TransformerInstructionExecutor(Register<T> pc, InstructionExecutor<T> instructionExecutor, InstructionTransformer<T> instructionTransformer) {
    this.pc = pc;
    this.instructionExecutor = instructionExecutor;
    this.instructionTransformer = instructionTransformer;
  }

  private InstructionTransformer<T> instructionTransformer;
  private InstructionActionExecutor<T> resetter = new InstructionActionExecutor<>(r -> r.reset());
  public Map<Instruction<T>, Instruction<T>> clonedInstructions = new HashMap<>();

  private Instruction<T> processTargetSource(Instruction<T> instruction) {
    instructionTransformer.virtualRegisterFactory.getRegisterNameBuilder().setCurrentAddress(getAddressOf(instruction));

    instructionTransformer.setCurrentInstruction(instruction);
    Instruction<T> cloned = instructionTransformer.clone(instruction);
    Instruction<T> tInstruction = clonedInstructions.get(instruction);
    if (tInstruction == null)
      clonedInstructions.put(instruction, cloned);
    else
      cloned = tInstruction;

    resetter.executeAction(cloned);

    return cloned;
  }

  @Override
  public Instruction<T> execute(Instruction<T> instruction) {
    Instruction<T> cloned = processTargetSource(instruction);
    //   if (isConcreteInstruction(cloned))
    instructionExecutor.execute(cloned);
    return cloned;
  }

  @Override
  public boolean isExecuting(Instruction<T> instruction) {
    return instructionExecutor.isExecuting(instruction);
  }

  private int getAddressOf(Instruction instruction) {
    return pc.read().intValue();
  }
}
