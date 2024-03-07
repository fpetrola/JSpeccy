package com.fpetrola.z80.instructions;

import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.instructions.cache.InstructionCloner;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Plain8BitRegister;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

import java.util.HashMap;
import java.util.Map;

import static com.fpetrola.z80.registers.RegisterName.VIRTUAL;

public class TransformerInstructionFetcher<T extends WordNumber> extends InstructionFetcherForTest<T> {
  private final CPUExecutionContext<T> context;
  private Map<Register, Register> targets = new HashMap<>();
  private InstructionCloner<T> instructionCloner;

  public TransformerInstructionFetcher(State<T> state, InstructionExecutor instructionExecutor, CPUExecutionContext<T> context) {
    super(state, instructionExecutor);
    this.context = context;
    instructionCloner = new InstructionCloner<>(new InstructionFactory(context.state));
  }

  public void fetchNextInstruction() {
    int pcValue = pc.read().intValue();
    Instruction<T> instruction = instructions.get(pcValue);
    processTargetSource(instruction, pcValue);

    updatePC(instruction);
  }

  private void processTargetSource(Instruction<T> instruction, int pcValue) {
    Instruction<T> cloned = instructionCloner.clone(instruction);

    if (cloned instanceof TargetInstruction<T> targetInstruction) {
      if (cloned instanceof TargetSourceInstruction targetSourceInstruction) {
        if (targetSourceInstruction.getSource() instanceof Register) {
          Register source = targets.get(targetSourceInstruction.getSource());
          targetSourceInstruction.setSource(source);
        }
      }
      if (targetInstruction.getTarget() instanceof Register register) {
        Register virtualRegister = createVirtualRegister(targetInstruction, targets.get(register));
        targets.put(register, virtualRegister);
        targetInstruction.setTarget(virtualRegister);
      } else {
        cloned.execute();
      }
    }
  }

  private Register createVirtualRegister(TargetInstruction<T> targetInstruction, Register register) {
    Register virtualRegister = new Plain8BitRegister<T>(VIRTUAL) {
      private boolean executing;

      public T read() {
        if (!executing) {
          executing = true;
          targetInstruction.execute();
          executing = false;
          return (T) data;
        } else
          return (T) register.read();
      }
    };
    return virtualRegister;
  }

}
