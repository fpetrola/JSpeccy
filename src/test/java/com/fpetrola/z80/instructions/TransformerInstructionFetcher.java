package com.fpetrola.z80.instructions;

import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.TargetInstruction;
import com.fpetrola.z80.instructions.base.TargetSourceInstruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

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
    if (pcValue == 0) {
      if (instruction instanceof TargetInstruction<T>) {
        TargetInstruction ld = (TargetInstruction) instruction;
        saveVirtualRegister(ld, instruction);
      }
    } else if (pcValue == 1) {
      if (instruction instanceof TargetSourceInstruction<T>) {
        TargetSourceInstruction ld = (TargetSourceInstruction) instruction;
        Register virtualRegister = targets.get(ld.getSource());
        ld.getTarget().write((T) virtualRegister.read());
        saveVirtualRegister(ld, instruction);
      }
    } else if (pcValue == 2) {
      if (instruction instanceof TargetSourceInstruction<T>) {
        TargetSourceInstruction ld = (TargetSourceInstruction) instruction;
        Register virtualRegister = targets.get(ld.getSource());
        ld.getTarget().write((T) virtualRegister.read());
      }
    }

    updatePC(instruction);
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
