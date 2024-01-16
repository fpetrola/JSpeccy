package com.fpetrola.z80;

import com.fpetrola.z80.instructions.AbstractOpCode;
import com.fpetrola.z80.instructions.InstructionCloner;
import com.fpetrola.z80.instructions.OpCode;
import com.fpetrola.z80.mmu.Memory;
import com.fpetrola.z80.registers.Register;

public class InstructionCache {
  MutableOpcode mutableOpcode = new MutableOpcode();

  public class CacheEntry {

    private OpCode opcode;

    public CacheEntry(OpCode opcode) {
      this.opcode = opcode;
    }

    public OpCode getOpcode() {
      return opcode;
    }

    public boolean isMutable() {
      return opcode == mutableOpcode;
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

  private OpCode[] opcodesCache = new OpCode[0x10000];

  private Runnable[] cacheInvalidators = new Runnable[0x10000];

  private InstructionCloner instructionCloner;

  public InstructionCache(Register pc, Memory memory) {
    instructionCloner = new InstructionCloner(pc);
    memory.setCacheInvalidators(cacheInvalidators);
  }

  public void cacheInstruction(int pcValue, OpCode instruction) {
    opcodesCache[pcValue] = (OpCode) instructionCloner.clone((AbstractOpCode) instruction);
    new InstructionCacheInvalidator(pcValue, instruction.getLength()).set();
  }

  public void reset() {
    opcodesCache = new OpCode[0x10000];
  }

  public OpCode getOpcodeAt(int pcValue) {
    return opcodesCache[pcValue];
  }

  public CacheEntry getCacheEntryAt(int pcValue) {
    return new CacheEntry(getOpcodeAt(pcValue));
  }
}
