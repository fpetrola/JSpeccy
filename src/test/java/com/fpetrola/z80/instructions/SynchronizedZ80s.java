package com.fpetrola.z80.instructions;

import com.fpetrola.z80.cpu.InstructionFetcher;
import com.fpetrola.z80.cpu.Z80Cpu;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class SynchronizedZ80s<T extends WordNumber> implements Z80Cpu<T> {
  private final CPUExecutionContext<? extends WordNumber> firstContext;
  private final CPUExecutionContext<? extends WordNumber> secondContext;

  public <T extends WordNumber> SynchronizedZ80s(CPUExecutionContext<T> firstContext, CPUExecutionContext<T> secondContext) {
    this.firstContext = firstContext;
    this.secondContext = secondContext;
  }

  @Override
  public void reset() {
    firstContext.z80.reset();
    secondContext.z80.reset();
  }

  @Override
  public void execute() {
    firstContext.z80.execute();
    secondContext.z80.execute();
  }

  @Override
  public void interruption() {

  }

  @Override
  public void endInterruption() {

  }

  @Override
  public InstructionFetcher getInstructionFetcher() {
    return null;
  }

  @Override
  public State<T> getState() {
    return null;
  }
}
