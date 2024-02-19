package com.fpetrola.z80.cpu;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.cache.InstructionCache;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.decoder.table.FetchNextOpcodeInstructionFactory;
import com.fpetrola.z80.opcodes.references.WordNumber;
import com.fpetrola.z80.spy.InstructionSpy;

public class CachedInstructionFetcher<T extends WordNumber> extends InstructionFetcher<T> {
  protected InstructionCache<T> instructionCache;

  public CachedInstructionFetcher(State aState, InstructionSpy spy) {
    super(aState, new FetchNextOpcodeInstructionFactory(spy, aState));
    instructionCache= new InstructionCache(aState.getMemory());
  }

  protected void fetchNextInstruction(InstructionExecutor<T> instructionExecutor) {
    InstructionCache.CacheEntry cacheEntry = instructionCache.getCacheEntryAt(pcValue);
    if (cacheEntry != null && !cacheEntry.isMutable()) {
      Instruction<T> instruction = cacheEntry.getOpcode();
      instructionExecutor.execute(instruction,opcodeInt, pcValue);
    } else {
      super.fetchNextInstruction(instructionExecutor);
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
