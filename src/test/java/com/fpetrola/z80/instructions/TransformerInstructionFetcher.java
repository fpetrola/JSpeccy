package com.fpetrola.z80.instructions;

import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;

import java.util.HashMap;
import java.util.Map;

public class TransformerInstructionFetcher<T extends WordNumber> extends InstructionFetcherForTest<T> {
  private final CPUExecutionContext<T> context;
  private Map<Register, Register> targets = new HashMap<>();

  public TransformerInstructionFetcher(State<T> state, InstructionExecutor instructionExecutor, CPUExecutionContext<T> context) {
    super(state, instructionExecutor);
    this.context = context;
  }


  public void fetchNextInstruction() {
    int pcValue = pc.read().intValue();
    Instruction<T> instruction = instructions.get(pcValue);
    processTargetSource(instruction);

    updatePC(instruction);
  }

  private void processTargetSource(Instruction<T> instruction) {
    TargetSourceInstruction targetSourceInstruction = (TargetSourceInstruction) instruction;

    if (targetSourceInstruction.getSource() instanceof Register) {
      Register virtualRegister = targets.get(targetSourceInstruction.getSource());
      targetSourceInstruction.getTarget().write((T) virtualRegister.read());
    }
    saveVirtualRegister(targetSourceInstruction, instruction);
  }

  private void saveVirtualRegister(TargetInstruction ld, Instruction<T> instruction) {
    Register target = (Register) ld.getTarget();
    targets.put(target, createVirtualRegister(instruction, target));
  }

  private DummyRegister createVirtualRegister(Instruction<T> instruction, Register target) {
    return new DummyRegister() {
      public Object read() {
        instruction.execute();
        return target.read();
      }
    };
  }
}
