package com.fpetrola.z80.instructions.cache;

import com.fpetrola.z80.instructions.base.InstructionFactory;
import com.fpetrola.z80.instructions.base.AbstractInstruction;
import com.fpetrola.z80.instructions.base.Instruction;
import com.fpetrola.z80.jspeccy.CacheInvalidatorMemoryWriteListener;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.opcodes.references.WordNumber;

import java.util.ArrayList;
import java.util.List;

public class InstructionCache<T extends WordNumber> {

  public class MutableOpcode extends CacheEntry {

    public MutableOpcode() {
    }

    public boolean isMutable() {
      return true;
    }
  }

  MutableOpcode mutableOpcode = new MutableOpcode();

  public class CacheEntry {

    private Instruction<T> opcode;

    public CacheEntry() {
    }

    public CacheEntry(Instruction<T> opcode) {
      this.opcode = opcode;
    }

    public Instruction<T> getOpcode() {
      return opcode;
    }

    public boolean isMutable() {
      return false;
    }

  }

  public class InstructionCacheInvalidator implements Runnable {
    private final T pcValue;
    private final int length;

    private InstructionCacheInvalidator(T pcValue, int length) {
      this.pcValue = pcValue;
      this.length = length;
    }

    public void run() {
      for (int j = 0; j < length; j++) {
        opcodesCache.set(pcValue.intValue() + j,  mutableOpcode);
        cacheInvalidators[pcValue.intValue() + j] = null;
      }
    }

    public void set() {
      for (int j = 0; j < length; j++)
        cacheInvalidators[pcValue.intValue() + j] = this;
    }
  }

  private List<CacheEntry> opcodesCache = new ArrayList<>();

  private Runnable[] cacheInvalidators = new Runnable[0x10000];

  private InstructionCloner instructionCloner;

  public InstructionCache(Memory memory, InstructionFactory instructionFactory) {
    instructionCloner = new InstructionCloner(instructionFactory);
    memory.setMemoryWriteListener(new CacheInvalidatorMemoryWriteListener(cacheInvalidators));
  }

  public void cacheInstruction(T pcValue, Instruction<T> instruction) {
    opcodesCache.set(pcValue.intValue(), new CacheEntry((Instruction<T>) instructionCloner.clone((AbstractInstruction<T>) instruction)));
    new InstructionCacheInvalidator(pcValue, instruction.getLength()).set();
  }

  public void reset() {
    opcodesCache = new ArrayList<>();
  }

  public CacheEntry getCacheEntryAt(T pcValue) {
    return opcodesCache.get(pcValue.intValue());
  }
}
