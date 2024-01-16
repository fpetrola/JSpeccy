package com.fpetrola.z80.opcodes.cache;

import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.AbstractInstruction;
import com.fpetrola.z80.opcodes.references.Instruction;
import com.fpetrola.z80.registers.Register;

public class InstructionCache {

  public class MutableOpcode extends CacheEntry {

    public MutableOpcode() {
    }

    public boolean isMutable() {
      return true;
    }
  }

  MutableOpcode mutableOpcode = new MutableOpcode();

  public class CacheEntry {

    private Instruction opcode;

    public CacheEntry() {
    }

    public CacheEntry(Instruction opcode) {
      this.opcode = opcode;
    }

    public Instruction getOpcode() {
      return opcode;
    }

    public boolean isMutable() {
      return false;
    }

  }

  public class InstructionCacheInvalidator implements Runnable {
    private final int pcValue;
    private final int length;

    private InstructionCacheInvalidator(int pcValue, int length) {
      this.pcValue = pcValue;
      this.length = length;
    }

    public void run() {
      for (int j = 0; j < length; j++) {
        opcodesCache[pcValue + j] = mutableOpcode;
        cacheInvalidators[pcValue + j] = null;
      }
    }

    public void set() {
      for (int j = 0; j < length; j++)
        cacheInvalidators[pcValue + j] = this;
    }
  }

  private CacheEntry[] opcodesCache = new CacheEntry[0x10000];

  private Runnable[] cacheInvalidators = new Runnable[0x10000];

  private InstructionCloner instructionCloner;

  public InstructionCache(Register pc, Memory memory) {
    instructionCloner = new InstructionCloner();
    memory.setCacheInvalidators(cacheInvalidators);
  }

  public void cacheInstruction(int pcValue, Instruction instruction) {
    opcodesCache[pcValue] = new CacheEntry((Instruction) instructionCloner.clone((AbstractInstruction) instruction));
    new InstructionCacheInvalidator(pcValue, instruction.getLength()).set();
  }

  public void reset() {
    opcodesCache = new CacheEntry[0x10000];
  }

  public CacheEntry getCacheEntryAt(int pcValue) {
    return opcodesCache[pcValue];
  }
}
