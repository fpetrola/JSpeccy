package com.fpetrola.z80.instructions;

import com.fpetrola.z80.cpu.InstructionExecutor;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.registers.Register;
import com.fpetrola.z80.registers.RegisterName;

public class TransformerInstructionFetcher<T extends WordNumber> extends InstructionFetcherForTest<T> {
  private final CPUExecutionContext<T> context;
  VirtualPlain8BitRegister<T> virtualH = null;

  public TransformerInstructionFetcher(State<T> state, InstructionExecutor instructionExecutor, CPUExecutionContext<T> context) {
    super(state, instructionExecutor);
    this.context = context;
  }

  public void fetchNextInstruction() {
    Register<T> h = state.r(RegisterName.H);
    Register<T> b = state.r(RegisterName.B);

    int pcValue = pc.read().intValue();
    if (pcValue == 0) {
      virtualH = context.cr(j2 -> new Ld(j2, context.ot.c(7), context.f()));
    } else if (pcValue == 1) {
      b.write(virtualH.read());
    }

    updatePC(instructions.get(pcValue));
  }
}
