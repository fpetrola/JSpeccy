package com.fpetrola.z80.cpu;

public interface InstructionFetcher {

  void fetchNextInstruction();
  void reset();
}
