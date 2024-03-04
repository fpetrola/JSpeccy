package com.fpetrola.z80.cpu;

import com.fpetrola.z80.instructions.InstructionFactory;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.cache.InstructionCache;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.decoder.table.FetchNextOpcodeInstructionFactory;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.spy.InstructionSpy;

public class CachedInstructionFetcher<T extends WordNumber> extends DefaultInstructionFetcher<T> {
  protected InstructionCache<T> instructionCache;

  public CachedInstructionFetcher(State aState, InstructionSpy spy, InstructionExecutor<T> instructionExecutor) {
    super(aState, new FetchNextOpcodeInstructionFactory(spy, aState), instructionExecutor);
    instructionCache= new InstructionCache(aState.getMemory(), new InstructionFactory(aState));
  }

  public void fetchNextInstruction() {
    InstructionCache.CacheEntry cacheEntry = instructionCache.getCacheEntryAt(pcValue);
    if (cacheEntry != null && !cacheEntry.isMutable()) {
      Instruction<T> instruction = cacheEntry.getOpcode();
      instructionExecutor.execute(instruction);
    } else {
      super.fetchNextInstruction();
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
