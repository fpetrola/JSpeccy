package com.fpetrola.z80.cpu;

public interface InstructionFetcher<T> {

  void fetchNextInstruction(InstructionExecutor<T> instructionExecutor);
  void reset();
}
