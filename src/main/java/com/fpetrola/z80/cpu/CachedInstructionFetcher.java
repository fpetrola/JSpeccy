package com.fpetrola.z80.cpu;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.base.DefaultInstructionFactory;
import com.fpetrola.z80.instructions.base.JumpInstruction;
import com.fpetrola.z80.instructions.cache.InstructionCache;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.decoder.table.FetchNextOpcodeInstructionFactory;
import com.fpetrola.z80.opcodes.references.OpcodeConditions;
import com.fpetrola.z80.opcodes.references.WordNumber;

public class CachedInstructionFetcher<T extends WordNumber> extends DefaultInstructionFetcher<T> {
  protected InstructionCache<T> instructionCache;

  public CachedInstructionFetcher(State aState, OpcodeConditions opcodeConditions, FetchNextOpcodeInstructionFactory fetchInstructionFactory, InstructionExecutor<T> instructionExecutor) {
    super(aState, opcodeConditions, fetchInstructionFactory, instructionExecutor, new DefaultInstructionFactory(aState));
    instructionCache = new InstructionCache(aState.getMemory(), new DefaultInstructionFactory(aState));
  }

  public void fetchNextInstruction() {
    pcValue = state.getPc().read();

    InstructionCache.CacheEntry cacheEntry = instructionCache.getCacheEntryAt(pcValue);
    if (cacheEntry != null && !cacheEntry.isMutable()) {
      Instruction<T> instruction = cacheEntry.getOpcode();
      instructionExecutor.execute(instruction);

      T nextPC = null;
      if (instruction instanceof JumpInstruction jumpInstruction)
        nextPC = (T) jumpInstruction.getNextPC();

      if (nextPC == null)
        nextPC = pcValue.plus(getBaseInstruction(instruction).getLength());

      state.getPc().write(nextPC);
    } else {
      super.fetchNextInstruction();
      if (cacheEntry == null || !cacheEntry.isMutable())
        instructionCache.cacheInstruction(pcValue, this.instruction);
    }
  }

  public void reset() {
    super.reset();
    instructionCache.reset();
  }
}
