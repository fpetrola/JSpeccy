package com.fpetrola.z80.cpu;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.cache.InstructionCache;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.spy.InstructionSpy;

import java.util.function.Consumer;

public class CachedInstructionFetcher extends InstructionFetcher {
  protected InstructionCache instructionCache;

  public CachedInstructionFetcher(State aState, InstructionSpy spy) {
    super(aState);
    instructionCache= new InstructionCache(aState.getMemory());
  }

  protected void fetchInstruction(Consumer<Instruction> instructionExecutor) {
    InstructionCache.CacheEntry cacheEntry = instructionCache.getCacheEntryAt(pcValue);
    if (cacheEntry != null && !cacheEntry.isMutable()) {
      Instruction instruction = cacheEntry.getOpcode();
      wrapExecution(instructionExecutor, instruction);
    } else {
      super.fetchInstruction(instructionExecutor);
      if (false)
        if (cacheEntry == null || !cacheEntry.isMutable())
          instructionCache.cacheInstruction(pcValue, this.instruction);
    }
  }

  public void reset() {
    super.reset();
    instructionCache.reset();
  }
}
