package com.fpetrola.z80;

import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.instructions.cache.InstructionCache;
import com.fpetrola.z80.mmu.State;
import com.fpetrola.z80.opcodes.decoder.table.TableBasedOpCodeDecoder;
import com.fpetrola.z80.spy.InstructionSpy;

import java.util.function.Consumer;

public class InstructionFetcher {
  private State state;
  protected Instruction instruction;

  private InstructionSpy spy;

  protected int opcodeInt;
  protected Instruction[] opcodesTables;
  protected InstructionCache instructionCache;
  protected int pcValue;
  public InstructionFetcher(State aState, InstructionSpy spy) {
    this.state = aState;
    this.spy = spy;
    spy.enable(false);
    opcodesTables = new TableBasedOpCodeDecoder(this.state, spy).getOpcodeLookupTable();
    instructionCache = new InstructionCache(state.getPc(), state.getMemory());
  }

  protected void fetchInstruction(Consumer<Instruction> instructionExecutor) {
    InstructionCache.CacheEntry cacheEntry = instructionCache.getCacheEntryAt(pcValue);

    if (cacheEntry != null && !cacheEntry.isMutable()) {
      Instruction instruction = cacheEntry.getOpcode();
      instructionExecutor.accept(instruction);
    } else {
//      System.out.println("exec: " + pcValue);
      opcodeInt = state.getMemory().read(pcValue);
      Instruction instruction = opcodesTables[this.state.isHalted() ? 0x76 : opcodeInt];
      instruction.setSpy(spy);
      spy.start(instruction, opcodeInt, pcValue);
      instructionExecutor.accept(instruction);
      spy.end();

      this.instruction = instruction.getBaseInstruction();
      if (false)
        if (cacheEntry == null || !cacheEntry.isMutable())
          instructionCache.cacheInstruction(pcValue, this.instruction);
    }
  }

  public void reset() {
    instructionCache.reset();
    spy.reset();
  }

  public InstructionSpy getSpy() {
    return spy;
  }
}
